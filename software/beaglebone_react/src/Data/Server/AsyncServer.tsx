import React from "react";
import { AppContext, IContext } from "../../Context";
import Logger, { LogSeverity, LogType } from "../../Logger";
import { Data, Device, ServerStatus } from "../DataTypes";
import './asyncServer.css';
import { IServer } from "./IServer";
import {default as OnlineServer} from "./SpringServer";
import {default as OfflineServer} from "./MockServer";

const AsyncServer = (props: {}): JSX.Element => {

    const context: IContext = React.useContext<IContext>(AppContext)
    const [abortCtr,setAbortCtr] = React.useState<AbortController>(new AbortController())
    const [reason, setReason] = React.useState<string>("")
    let timer = React.useRef<NodeJS.Timeout | undefined>()
    const logger = Logger(context.logLevel)
    let timeSince = React.useRef<number[]>([0,0])
    let server: IServer = isOffline(context.serverAddress)?OfflineServer:OnlineServer


    React.useEffect(() => {
        const _serverBusy = () => {
            context.setServerStatus(ServerStatus.BUSY)
            logger.Log(LogType.API,LogSeverity.WARNING,"slow server, waiting for "+((new Date()).getTime() - timeSince.current[0])/1000+" seconds")
        }    
        const _handleApiError = (reason: any) => {
            timeSince.current = [0,0]
            setAbortCtr(new AbortController())
            context.setServerStatus(ServerStatus.DISCONNECTED)
            logger.Log(LogType.API,LogSeverity.ERROR,reason)
            setReason(reason)
        }
        if (context.serverStatus === ServerStatus.INIT){
            if (timeSince.current[0] || timeSince.current[1]) {
                timeSince.current[0] = 0;
                timeSince.current[1] = 0;
                abortCtr.abort()
                logger.Log(LogType.API,LogSeverity.INFO,"Aborted API Call")
            }
        }
        // If disconnected, ping server
        if (context.serverStatus === ServerStatus.DISCONNECTED || 
            context.serverStatus === ServerStatus.INIT) {
            timer.current = setInterval(() => {
                console.log(timeSince.current);
                
                if (timeSince.current[0] || timeSince.current[1]) return _serverBusy()
                
                timeSince.current[0] = (new Date()).getTime()
                logger.Log(LogType.API,LogSeverity.INFO,"Pinging Server "+(isOffline(context.serverAddress)?"(Offline)":"(Online)"))
                server.PingServer(abortCtr).then((result: boolean) => {
                    timeSince.current = [0,0]
                    logger.Log(LogType.API,result?LogSeverity.SUCCESS:LogSeverity.ERROR,"Setting Server Status: "+(result?ServerStatus.CONNECTED:ServerStatus.DISCONNECTED));
                    context.setServerStatus(result ? ServerStatus.CONNECTED : ServerStatus.DISCONNECTED)
                }).catch(_handleApiError)
            }, context.pollingInterval);
        }
        // If connected, poll new data
        else if (context.serverStatus === ServerStatus.CONNECTED && context.pollingInterval > 0) {
            timer.current = setInterval(() => {
                if (timeSince.current[0] || timeSince.current[1]) return _serverBusy()

                timeSince.current = [(new Date()).getTime(),(new Date()).getTime()]
                logger.Log(LogType.API,LogSeverity.INFO,"Getting Data and Devices")
                server.GetData(abortCtr, context.data.size).then((incomingData: Data[]) => {
                    context.setServerStatus(ServerStatus.CONNECTED)
                    timeSince.current[0] = 0
                    let newDataMap = new Map(context.data)
                    incomingData.forEach(data => {
                        newDataMap.set(data.id,data)
                    });
                    context.setData(newDataMap)
                    logger.Log(LogType.API,LogSeverity.SUCCESS,"Got Data")
                }).catch(_handleApiError)

                server.GetDevices(abortCtr).then((incomingDevices: Device[]) => {
                    context.setServerStatus(ServerStatus.CONNECTED)
                    timeSince.current[1] = 0
                    let newDeviceMap = new Map<number,Device>()
                    incomingDevices.forEach(device => {
                        newDeviceMap.set(device.id,device)
                    });
                    context.setDevices(newDeviceMap)
                    logger.Log(LogType.API,LogSeverity.SUCCESS,"Got Devices")
                })
                .catch(_handleApiError)
                
            }, context.pollingInterval);
        }
        // Clears the Interval if there's anything still running on update
        return () => { if (timer.current) clearInterval(timer.current) }
    }, [abortCtr, context, context.pollingInterval, context.serverStatus, logger, server]);


    return (
        <div className="Container" style={{backgroundColor: getBgColor(context.serverStatus)}}>
            <p className="Text">{getBannerText(context.serverStatus, context.serverAddress, reason)}</p>
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

function getBannerText(serverStatus: ServerStatus, serverAddress: string, reason: string): string {
    const mode = (isOffline(serverAddress) ? " (Offline)":" (Online)")
    const reasonText = (serverStatus === ServerStatus.DISCONNECTED ? (" Reason: "+reason) : "")
    return "Server Status: " + serverStatus + reasonText + mode
}

export function isOffline(serverAddress:string): boolean {
    return serverAddress.slice(0,1) === '_'
}

export default AsyncServer