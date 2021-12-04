import { Data, Device } from "../DataTypes";

export interface IServer {
    PingServer: () => Promise<boolean>
    GetData: (startId: number) => Promise<Data[]>
    GetDevices: () => Promise<Device[]>
}