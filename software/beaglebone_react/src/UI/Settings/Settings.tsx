import React from 'react';
import { AppContext, defaultContext } from '../../Context';
import { ServerStatus } from '../../Data/DataTypes';
import { isOffline } from '../../Data/Server/AsyncServer';
import Logger, { LogSeverity, LogType } from '../../Logger';
import { toNumber } from '../Search/Search';
import './settings.css'
import {default as OnlineServer} from "./../../Data/Server/SpringServer";
import { IServer } from '../../Data/Server/IServer';

 
const SettingsView = () => {  

    const context = React.useContext(AppContext)

    const _isOffline = isOffline(context.serverAddress)

    const [pollingRate, setPollingRate] = React.useState<number>(context.pollingInterval)
    const _unequalRates = pollingRate !== context.pollingInterval
    const logger = Logger(context.logLevel)

    let server: IServer = OnlineServer

    if (context.data.size > 1000) {
        context.setData(new Map())
        server.ClearDB() 
    }


    return (
        <div className="SettingsContainer">
            <div className="SettingsHeader">
                <h1>App Settings</h1>
            </div>
            <div className="SettingsBody">
                <div className="SettingRow">
                    <p className="SettingLabel">Server Address</p>
                    <input value={_isOffline?"Offline":context.serverAddress} disabled={_isOffline} onChange={
                        (text) => context.setServerAddress(text.target.value)
                    }/>
                    <div className="SettingButton" onClick={ () => {
                        logger.Log(LogType.SET,LogSeverity.INFO,"Changing to "+(_isOffline?"Online":"Offline"))
                        context.setData(defaultContext.data!)
                        context.setDevices(defaultContext.devices!)
                        context.setServerStatus(ServerStatus.INIT)
                        context.setServerAddress(_isOffline?(context.serverAddress.slice(1)):("_"+context.serverAddress))
                    }
                    }><p className="SettingButtonLabel">{_isOffline?"Go Online":"Go Offline"}</p></div>
                </div>
                <div className="SettingRow">
                    <p className="SettingLabel">Server Polling Rate (ms)</p>
                    <input value={(_unequalRates?"*":" ")+pollingRate} onChange={
                        (text) => setPollingRate(toNumber(text.target.value.slice(1))??1000)
                    }/>
                    <div className="SettingButton" onClick={
                        () => context.setPollingInterval(pollingRate)
                    }><p className="SettingButtonLabel">Set</p></div>
                </div>
                <div className="SettingRow">
                    <p className="SettingLabel">Clear DB</p>
                    <div className="SettingButton" onClick={
                        () => server.ClearDB()
                    }><p className="SettingButtonLabel">Byyyyee</p></div>
                </div>
            </div>
        </div>
    );
}

export default SettingsView