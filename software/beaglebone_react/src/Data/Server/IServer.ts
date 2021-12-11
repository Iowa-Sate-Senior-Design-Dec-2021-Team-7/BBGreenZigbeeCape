import { Data, DataType, Device } from "../DataTypes";

export interface IServer {
    PingServer: (abortController: AbortController) => Promise<boolean>
    GetData: (abortController: AbortController ,startId: number) => Promise<Data[]>
    GetDevices: (abortController: AbortController) => Promise<Device[]>
    ClearDB: () => Promise<null>
}

export type dataPayload = {
    id_db: number,
    type: DataType,
    value: number
    timestamp: string,
    device: {id_network: string}
}

export function getDataFromPayload(payload:dataPayload[]): Data[]  {
    
    return payload.map(pl=> { return {
        id_db: pl.id_db,
        timestamp: new Date(pl.timestamp),
        value: pl.value,
        type: pl.type,
        device: pl.device.id_network,
    }})
}