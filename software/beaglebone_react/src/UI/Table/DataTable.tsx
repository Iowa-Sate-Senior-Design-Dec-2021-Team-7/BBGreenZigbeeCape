import React from "react";
import { AppContext } from "../../Context";
import SearchView from "../Search/Search";
import './table.css'
import { ChartHeader, ChartRow, ChartSettingsRow } from "./TableComponents";

const DataTableView = () => {
    const {data} = React.useContext(AppContext)

    const [autoScroll, setAutoScroll] = React.useState<boolean>(true)
    const [showSearch, setShowSearch] = React.useState<boolean>(false)
    const [minId, setMinId] = React.useState<number|null>(null)
    const [maxId, setMaxId] = React.useState<number|null>(null)
    
    const filteredData = Array.from(data.values()).filter(e => (minId == null || e.id >= minId) && (maxId == null || e.id <= maxId))
    
    return (
        <div className="Chart">
            <ChartSettingsRow autoScroll={autoScroll} setAutoScroll={setAutoScroll} showSearch={showSearch} setShowSearch={setShowSearch} />
            <div className="ChartHead">
                <ChartHeader label="Id"/>
                <ChartHeader label="DataType"/>
                <ChartHeader label="Value"/>           
            </div>
            <div className="ChartBody">
                { filteredData.map((d,index) => <ChartRow key={"ChartRow_"+index} data={d} autoScroll={autoScroll} /> ) }
            </div>
            {showSearch ? (
                <SearchView 
                    closeOnClick={setShowSearch}
                    minId={minId}
                    setMinId={setMinId}
                    maxId={maxId}
                    setMaxId={setMaxId}
                    />
            ): null }
        </div>
    );
}

export default DataTableView