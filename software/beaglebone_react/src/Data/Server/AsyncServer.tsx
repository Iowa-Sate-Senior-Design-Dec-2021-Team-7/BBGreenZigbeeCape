import React from "react";
import { AppContext, IContext } from "../../Context";
import Logger, { LogSeverity, LogType } from "../../Logger";
import { Data, ServerStatus } from "../DataTypes";
import './asyncServer.css';
import { IServer } from "./IServer";
// import Server from "./MockServer";

const AsyncServer = (props: {Server: IServer}): JSX.Element => {

    const context: IContext = React.useContext<IContext>(AppContext)
    let timer = React.useRef<NodeJS.Timeout | undefined>()
    const logger = Logger(context.logLevel)
    let timeSince = React.useRef<number>(0)

    React.useEffect(() => {    
        const _serverBusy = () => {
            context.setServerStatus(ServerStatus.BUSY)
            logger.Log(LogType.API,LogSeverity.WARNING,"slow server, waiting for "+((new Date()).getTime() - timeSince.current)/1000+" seconds")
        }    
        // If disconnected, ping server
        if (context.serverStatus === ServerStatus.DISCONNECTED) {
            timer.current = setInterval(() => {
                if (timeSince.current) return _serverBusy()

                logger.Log(LogType.API,LogSeverity.INFO,"Pinging Server")
                props.Server.PingServer().then((result: boolean) => {
                    logger.Log(LogType.API,result?LogSeverity.SUCCESS:LogSeverity.ERROR,"Setting Server Status: "+(result?ServerStatus.CONNECTED:ServerStatus.DISCONNECTED));
                    context.setServerStatus(result ? ServerStatus.CONNECTED : ServerStatus.DISCONNECTED)
                })
            }, context.pollingInterval);
        }
        // If connected, poll new data
        else if (context.serverStatus === ServerStatus.CONNECTED && context.pollingInterval > 0) {
            timer.current = setInterval(() => {
                if (timeSince.current) return _serverBusy()

                timeSince.current = (new Date()).getTime()
                logger.Log(LogType.API,LogSeverity.INFO,"Getting Data")
                props.Server.GetData(context.data.size).then((incomingData: Data[]) => {
                    context.setServerStatus(ServerStatus.CONNECTED)
                    timeSince.current = 0
                    let newDataMap = new Map(context.data)
                    incomingData.forEach(data => {
                        context.setData(newDataMap.set(data.id,data))
                    });
                    logger.Log(LogType.API,LogSeverity.SUCCESS,"Got Data")
                })
                
            }, context.pollingInterval);
        }
        // Clears the Interval if there's anything still running on update
        return () => { if (timer.current) clearInterval(timer.current) }
    }, [context, context.pollingInterval, context.serverStatus, logger, props.Server]);


    return (
        <div className="Container" style={{backgroundColor: getBgColor(context.serverStatus)}}>
            <p className="Text">{getBannerText(context.serverStatus)}</p>
        </div>
    )

}

function getBgColor(serverStatus:ServerStatus): string {
    switch (serverStatus) {
        case ServerStatus.CONNECTED: return "green"
        case ServerStatus.DISCONNECTED: return "red"
        case ServerStatus.BUSY: return "blue"
        default: return "purple"
    }
}

function getBannerText(serverStatus: ServerStatus): string {
    return "Server Status: " + serverStatus
}

export default AsyncServer