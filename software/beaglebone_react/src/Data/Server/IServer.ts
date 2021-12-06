import { Data, Device } from "../DataTypes";

export interface IServer {
    PingServer: (abortController: AbortController) => Promise<boolean>
    GetData: (abortController: AbortController ,startId: number) => Promise<Data[]>
    GetDevices: (abortController: AbortController) => Promise<Device[]>
}