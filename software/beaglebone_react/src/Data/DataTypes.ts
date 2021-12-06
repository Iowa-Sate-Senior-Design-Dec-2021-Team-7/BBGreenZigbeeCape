
export interface Data {
    id: number,
    dataType: DataType,
    value: number
}

export interface Device {
    id: number,
    deviceType: string,
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
