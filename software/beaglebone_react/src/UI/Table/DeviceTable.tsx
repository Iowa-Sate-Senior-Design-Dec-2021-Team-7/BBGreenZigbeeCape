import React from "react";
import { AppContext } from "../../Context";
import './table.css'
import { ChartHeader, ChartRow } from "./TableComponents";

const DeviceTableView = () => {
    const {devices} = React.useContext(AppContext)

    // const [autoScroll, setAutoScroll] = React.useState<boolean>(true)
    // const [showSearch, setShowSearch] = React.useState<boolean>(false)
    // const [minId, setMinId] = React.useState<number|null>(null)
    // const [maxId, setMaxId] = React.useState<number|null>(null)
    
    const filteredData = Array.from(devices.values()).filter(d => true)
    
    return (
        <div className="Chart">
            {/* <ChartSettingsRow autoScroll={autoScroll} setAutoScroll={setAutoScroll} showSearch={showSearch} setShowSearch={setShowSearch} /> */}
            <div className="ChartHead">
                <ChartHeader label="Id"/>       
                <ChartHeader label="Device Type"/>       
            </div>
            <div className="ChartBody">
                { filteredData.map((data,index) => <ChartRow key={"ChartRow_"+index} data={data} /> ) }
            </div>
        </div>
    );
}

export default DeviceTableView