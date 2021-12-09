import { Data, Device } from "../DataTypes"
import { IServer } from "./IServer"

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const SERVER_IP: string = '10.29.160.230:8080/'
export const SERVER_URL: string = 'http://sddec21-proj07.ece.iastate.edu:8080/'

const Server: IServer = {
    PingServer: function (abortController: AbortController): Promise<boolean> {
        const endpoint: string = SERVER_URL
        return new Promise<boolean>((resolve, reject) => {
            fetch(endpoint, {
                method: "GET",
                signal: abortController.signal,
            })
            .then(async response => {
                resolve(response.ok)
            })
            .catch(reason => reject(reason))
        })
    },
    GetData: function (abortController: AbortController): Promise<Data[]> {
        const endpoint: string = SERVER_URL + 'api/get-payloads'
        return new Promise<Data[]>((resolve, reject) => {
            fetch(endpoint, {
                method: "GET",
                signal: abortController.signal,
            })
            .then(async response => {
                const text = await response.text()
                const data: Data[] = JSON.parse(text).payload.map((device: { id_db: number, type_device: string }): Device => { return {
                    id: device.id_db,
                    deviceType: device.type_device
                }});
                resolve(data)
            })
            .catch(reason => reject(reason))
        })
    },
    GetDevices: function (abortController: AbortController ): Promise<Device[]> {
        const endpoint: string = SERVER_URL + 'api/get-devices'
        return new Promise<Device[]>((resolve, reject) => {
            fetch(endpoint, {
                method: "GET",
                signal: abortController.signal,
            })
            .then(async response => {
                const text = await response.text()
                const devices: Device[] = JSON.parse(text).payload.map((device: { id_db: number, type_device: string }): Device => { return {
                    id: device.id_db,
                    deviceType: device.type_device
                }});
                resolve(devices)
            })
            .catch(reason => reject(reason))
        })
    }
}

export default Server