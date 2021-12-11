
import React from "react";
import { Data, Device, ServerStatus } from "./Data/DataTypes";
import { SERVER_URL } from "./Data/Server/SpringServer";
import { LogSeverity } from "./Logger";

export interface IContext {
    logLevel: LogSeverity
    setLogLevel: React.Dispatch<React.SetStateAction<LogSeverity>>
    data: Map<number,Data>
    setData: React.Dispatch<React.SetStateAction<Map<number,Data>>>
    devices: Map<number,Device>
    setDevices: React.Dispatch<React.SetStateAction<Map<number, Device>>>
    // How many ms between each server poll. Set to 0 to disable
    pollingInterval: number
    setPollingInterval: React.Dispatch<React.SetStateAction<number>>
    serverStatus: ServerStatus
    setServerStatus: React.Dispatch<React.SetStateAction<ServerStatus>>
    serverAddress: string
    setServerAddress: React.Dispatch<React.SetStateAction<string>>
}

export const defaultContext: Partial<IContext> = {
    logLevel: LogSeverity.INFO,
    data: new Map<number,Data>(),
    devices: new Map<number,Device>(),
    pollingInterval: 1000,
    serverStatus: ServerStatus.INIT,
    serverAddress: SERVER_URL
}

const AppContext = React.createContext<IContext>({} as IContext);

const ContextWrapper: React.FC = ({ children }) => {

    const [logLevel, setLogLevel] = React.useState<LogSeverity>(defaultContext.logLevel!)
    const [data, setData] = React.useState<Map<number,Data>>(defaultContext.data!)
    const [devices, setDevices] = React.useState<Map<number,Device>>(defaultContext.devices!)
    const [pollingInterval, setPollingInterval] = React.useState<number>(defaultContext.pollingInterval!)
    const [serverStatus, setServerStatus] = React.useState<ServerStatus>(defaultContext.serverStatus!)
    const [serverAddress, setServerAddress] = React.useState<string>(defaultContext.serverAddress!)

    return <AppContext.Provider value={{logLevel, setLogLevel, data, setData, devices, setDevices, pollingInterval, setPollingInterval, serverStatus, setServerStatus, serverAddress, setServerAddress}}>
        {children}
    </AppContext.Provider>
}

export { AppContext }
export default ContextWrapper