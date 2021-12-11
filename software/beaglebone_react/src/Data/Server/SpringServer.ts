import { Data, Device } from "../DataTypes"
import { getDataFromPayload, IServer } from "./IServer"

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
                .then(async (response) => {
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
                .then(async (response) => {
                    const text = await response.text()

                    const data: Data[] = getDataFromPayload(JSON.parse(text).payload)
                    resolve(data)
                })
                .catch(reason => reject(reason))
        })
    },
    GetDevices: function (abortController: AbortController): Promise<Device[]> {
        const endpoint: string = SERVER_URL + 'api/get-devices'
        return new Promise<Device[]>((resolve, reject) => {
            fetch(endpoint, {
                method: "GET",
                signal: abortController.signal,
            })
                .then(async (response) => {
                    const text = await response.text()

                    const devices: Device[] = JSON.parse(text).payload
                    resolve(devices)
                })
                .catch(reason => reject(reason))
        })
    },
    ClearDB: function (): Promise<null> {
        const endpoint: string = SERVER_URL + 'api/delete-all'
        return new Promise<null>((resolve, reject) => {
            fetch(endpoint, {
                method: "DELETE",
            })
                .then(async (response) => {
                    resolve(null)
                })
                .catch(reason => reject(reason))
        })
    }
}

export default Server