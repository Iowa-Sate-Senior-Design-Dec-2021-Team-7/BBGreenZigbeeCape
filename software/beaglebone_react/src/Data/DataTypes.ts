
export interface Data {
    id_db: number,
    type: DataType,
    value: number
    timestamp: Date,
    device: string,
}

export interface Device {
    id_db: number,
    id_network: string,
    type_device: string,
    type_data: string,
}

export enum DataType {
    TEMP = "TEMP",
    LIGHT = "LIGHT",
}

export enum ServerStatus {
    INIT = "INITIALIZING",
    DISCONNECTED = "DISCONNECTED",
    CONNECTED = "CONNECTED",
    BUSY = "BUSY",
}

export enum DeviceType {
    SENSOR = "SENSOR"
}
