import { Data, DataType, Device } from "../DataTypes"
import { IServer } from "./IServer"

const MAX_SERVER_DELAY = 500

const Server: IServer = {
    PingServer: function (): Promise<boolean> {
        return new Promise<boolean>((resolve, reject) => {
            setTimeout(() => resolve(true), MAX_SERVER_DELAY * Math.random())
        })
    },

    GetData: function (startId: number = 0): Promise<Data[]> {
        return new Promise<Data[]>((resolve, reject) => {
            const newData: Data = GenRandomTempData(startId)
            setTimeout(() => resolve([newData]), MAX_SERVER_DELAY * Math.random())
        })
    },
    
    GetDevices: function (): Promise<Device[]> {
        return new Promise<Device[]>((resolve, reject) => {
            // 10.29.160.230
            fetch('http://sddec21-proj07.ece.iastate.edu:8080/api/get-devices', {
                method: "GET",
            })
            .then(async response => {
                console.log("Response:",response);
                
                // let id = 0
                const text = await response.text()
                // .then((text) => {
                //     const devices: Device[] = JSON.parse(text).payload.map((device: { id_db: number }) => { return {
                //         id: device.id_db
                //     }});
                    // console.log("Devices:",text);
                    
                //     resolve(devices)
                // }).catch(reason => console.log(reason))
                const devices: Device[] = JSON.parse(text).payload.map((device: { id_db: number, type_device: string }): Device => { return {
                    id: device.id_db,
                    deviceType: device.type_device
                }});
                console.log("Devices:",devices);
                
                resolve(devices)
            })
            .catch(reason => reject(reason))
        })
    }
}

const GenRandomTempData = (startId: number): Data => {
    return {
        id: startId,
        dataType: DataType.TEMP,
        value: Math.floor(100*Math.random()),
    }
}

export default Server