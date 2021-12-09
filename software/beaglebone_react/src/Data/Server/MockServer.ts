import { Data, DataType, Device, DeviceType } from "../DataTypes"
import { IServer } from "./IServer"

const MAX_SERVER_DELAY = 500

const Server: IServer = {
    PingServer: function (_): Promise<boolean> {
        return new Promise<boolean>((resolve, reject) => {
            setTimeout(() => resolve(true), MAX_SERVER_DELAY * Math.random())
        })
    },

    GetData: function (_, startId: number = 0): Promise<Data[]> {
        return new Promise<Data[]>((resolve, _) => {
            const newData: Data[] = GenRandomTempData(1,startId)
            setTimeout(() => resolve(newData), MAX_SERVER_DELAY * Math.random())
        })
    },
    
    GetDevices: function (_): Promise<Device[]> {
        return new Promise<Device[]>((resolve, _) => {
            const newDevices: Device[] = GenRandomDevices(3)
            setTimeout(() => resolve(newDevices), MAX_SERVER_DELAY * Math.random())
        })
    }
}

const GenRandomTempData = (num: number, startId: number): Data[] => {
    return [...Array(num)].map((_,id) => { return {
        id: startId+id,
        dataType: DataType.TEMP,
        value: Math.floor(100*Math.random()),
    }})
}

const GenRandomDevices = (num: number): Device[] => {
    return [...Array(num)].map((_,id) => { return {
         id: id,
         deviceType: DeviceType.SENSOR,
    }})
}

export default Server