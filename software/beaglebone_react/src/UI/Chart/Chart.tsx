import React from "react";
import { AppContext } from "../../Context";
import { Data } from "../../Data/DataTypes";
import SearchView from "../Search/Search";
import './chart.css'

const ChartView = () => {
    const {data} = React.useContext(AppContext)

    const [autoScroll, setAutoScroll] = React.useState<boolean>(false)
    const [showSearch, setShowSearch] = React.useState<boolean>(true)
    const [minId, setMinId] = React.useState<number|null>(null)
    const [maxId, setMaxId] = React.useState<number|null>(null)

    console.log("min",minId,"max",maxId);
    
    const filteredData = Array.from(data.values()).filter(e => (minId == null || maxId == null) || (e.id >= minId && e.id <= maxId))
    console.log("Filtered:",filteredData,"All:",Array.from(data.values()));
    
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

const ChartSettingsRow = (props: {
    autoScroll: boolean,
    setAutoScroll: (value: boolean) => void,
    showSearch: boolean,
    setShowSearch: (value: boolean) => void,
}): JSX.Element => {
    return (
        <div className="ChartSettingsRow">
            <div className="ChartSettingsRowButton" onClick={() => props.setAutoScroll(!props.autoScroll)}>
                <p className="Label">{(props.autoScroll ? "Disable":"Enable")} Auto Scroll</p>
            </div>
            <div className="ChartSettingsRowButton" onClick={() => props.setShowSearch(!props.showSearch)}>
                <p className="Label">{(props.showSearch ? "Hide":"Show")} Search/Filter</p>
            </div>
        </div>
    )
}

const ChartHeader = (props: {label: string}) => {
    return (
       <div className="ChartCell">
           <p className="Label">{props.label}</p>
       </div>
    );
}

const ChartRow = (props: {data: Data, autoScroll: boolean}) => {
    const fieldRef = React.useRef<HTMLInputElement>(null);
    React.useEffect(() => {
        if (props.autoScroll) fieldRef.current?.scrollIntoView({ behavior: "smooth" })
    })
    const className = "ChartRow" + (props.data.id%2 ? "" : " ChartRowEven")
    return (
        <div className={className} ref={fieldRef}>
            <ChartCell value={props.data.id} />
            <ChartCell value={props.data.dataType} />
            <ChartCell value={props.data.value} />
        </div>
    )
}

const ChartCell = (props: {value: any}) => {
    return (
        <div className="ChartCell">
            <p className="Label">{props.value}</p>
        </div>
    )
}

export default ChartView