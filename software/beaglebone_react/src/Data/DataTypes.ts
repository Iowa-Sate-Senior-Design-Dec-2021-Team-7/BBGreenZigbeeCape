
export interface Data {
    id: number,
    dataType: DataType,
    value: number
}

export enum DataType {
    TEMP = "TEMP",
    LIGHT = "LIGHT",
}
