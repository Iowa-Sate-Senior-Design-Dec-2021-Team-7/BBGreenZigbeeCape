import { Data, DataType } from "../DataTypes"
import { IServer } from "./IServer"

const MAX_SERVER_DELAY = 500

const Server: IServer = {
    PingServer: function (): Promise<boolean> {
        return new Promise<boolean>((resolve, reject) => {
            setTimeout(() => resolve(true), MAX_SERVER_DELAY*Math.random())
        })
    }, 

    GetData: function (startId: number = 0): Promise<Data[]> {
        return new Promise<Data[]>((resolve, reject) => {
            const newData: Data = GenRandomTempData(startId)
            setTimeout(() => resolve([newData]), MAX_SERVER_DELAY*Math.random())
        })
    },
}

const GenRandomTempData = (startId: number): Data => {
    return {
        id: startId,
        dataType: DataType.TEMP,
        value: Math.floor(100*Math.random()),
    }
}

export default Server