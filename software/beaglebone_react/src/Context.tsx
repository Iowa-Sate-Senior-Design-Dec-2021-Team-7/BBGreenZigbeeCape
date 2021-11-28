
import React from "react";
import { Data, ServerStatus } from "./Data/DataTypes";
import { LogSeverity } from "./Logger";

export interface IContext {
    logLevel: LogSeverity
    setLogLevel: React.Dispatch<React.SetStateAction<LogSeverity>>
    data: Map<number,Data>
    setData: React.Dispatch<React.SetStateAction<Map<number,Data>>>
    // How many ms between each server poll. Set to 0 to disable
    pollingInterval: number
    setPollingInterval: React.Dispatch<React.SetStateAction<number>>
    serverStatus: ServerStatus
    setServerStatus: React.Dispatch<React.SetStateAction<ServerStatus>>
}

const defaultContext: Partial<IContext> = {
    logLevel: LogSeverity.INFO,
    data: new Map<number,Data>(),
    pollingInterval: 1000,
    serverStatus: ServerStatus.DISCONNECTED,
}

const AppContext = React.createContext<IContext>({} as IContext);

const ContextWrapper: React.FC = ({ children }) => {

    const [logLevel, setLogLevel] = React.useState<LogSeverity>(defaultContext.logLevel!)
    const [data, setData] = React.useState<Map<number,Data>>(defaultContext.data!)
    const [pollingInterval, setPollingInterval] = React.useState<number>(defaultContext.pollingInterval!)
    const [serverStatus, setServerStatus] = React.useState<ServerStatus>(defaultContext.serverStatus!)

    return <AppContext.Provider value={{logLevel, setLogLevel, data, setData, pollingInterval, setPollingInterval, serverStatus, setServerStatus}}>
        {children}
    </AppContext.Provider>
}

export { AppContext }
export default ContextWrapper