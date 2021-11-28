
export interface Data {
    id: number,
    dataType: DataType,
    value: number
}

export enum DataType {
    TEMP = "TEMP",
    LIGHT = "LIGHT",
}

export enum ServerStatus {
    DISCONNECTED = "DISCONNECTED",
    CONNECTED = "CONNECTED",
    BUSY = "BUSY",
}
