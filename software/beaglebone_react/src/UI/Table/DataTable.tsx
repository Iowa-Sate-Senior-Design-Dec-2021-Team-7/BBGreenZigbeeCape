import React from "react";
import { AppContext } from "../../Context";
import SearchView from "../Search/Search";
import './table.css'
import { ChartHeader, ChartRow, ChartSettingsRow } from "./TableComponents";
import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend
  } from "recharts";
import { Data, Device } from "../../Data/DataTypes";

const DataTableView = () => {
    const {data, devices} = React.useContext(AppContext)

    const [autoScroll, setAutoScroll] = React.useState<boolean>(true)
    const [showSearch, setShowSearch] = React.useState<boolean>(false)
    const [minId, setMinId] = React.useState<number|null>(null)
    const [maxId, setMaxId] = React.useState<number|null>(null)
    const [showGraph, setShowGraph] = React.useState<boolean>(false)
    
    const filteredData = Array.from(data.values()).filter(e => (minId == null || e.id_db >= minId) && (maxId == null || e.id_db <= maxId))

    const graphData: GraphData[] = _getGraphData(Array.from(data.values()), Array.from(devices.values()))
    
    return (
        <div className="Chart">
            <ChartSettingsRow autoScroll={autoScroll} setAutoScroll={setAutoScroll} showSearch={showSearch} setShowSearch={setShowSearch} showGraph={showGraph} setShowGraph={setShowGraph}/>
            {!showGraph ? <div className="ChartHead">
                <ChartHeader label={"Id (Showing "+data.size+")"}/>
                <ChartHeader label="Time"/>
                <ChartHeader label="DataType"/>
                <ChartHeader label="Value"/>           
                <ChartHeader label="Device"/>       
            </div> : null }
            <div className="ChartBody">
                {showGraph ? <Graph data={graphData}/> : filteredData.map((d,index) => <ChartRow key={"ChartRow_"+index} data={d} autoScroll={autoScroll} /> ) }
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

const lineColor1 = "#ff7300"
const lineColor2 = "#00ffff"

const Graph = (props: {data: GraphData[]}) => {
    console.log("ACTUAL GRAPH:",props.data);

    return (
        <LineChart
  width={1000}
  height={500}
  data={props.data}
  margin={{ top: 5, right: 20, left: 10, bottom: 5 }}>
    <Tooltip />
    <CartesianGrid stroke="#f5f5f5" />
    <XAxis dataKey="name" />
    <YAxis />
    {props.data.map((data,i) => 
        <Line key={"line_"+i} type="monotone" dataKey={"line"+(i+1)} stroke={i%2?lineColor1:lineColor2} />
    )}
  {/* <Line type="monotone" dataKey="pv" stroke="#387908" yAxisId={1} /> */}
</LineChart>
    )
}

export type GraphData = {
    name: number,
    line1: any | undefined,
    line2: any | undefined,
}

function _getGraphData(data:Data[], devices: Device[]): GraphData[] {
    
    // console.log(data.slice(-30));
    
    // const recentData = data.filter(d => Date.now() - d.timestamp.getTime() < 100000 ) // Last 10 seconds
    const recentData = data.sort((d1,d2) => d1.timestamp.getTime() - d2.timestamp.getTime()).slice(-30) // Last 10 seconds
    // const recentData = data.slice(-30)
    // console.log("RecentData:",recentData);
    let graphData: GraphData[] = recentData.map(data => {return {name: data.timestamp.getTime(), line1: null, line2: null}})
    devices.forEach((device,i) => {
        const deviceData = recentData.filter(d => d.device === device.id_network).slice(-10)
        // console.log("Graph:",graphData);
        
        graphData.forEach(d => {
            if (i) d.line2 = deviceData.find(data => data.timestamp.getTime() === d.name)?.value
            else d.line1 = deviceData.find(data => data.timestamp.getTime() === d.name)?.value
        })
        // graphData.forEach(d => d.name = new Date(d.name).getSeconds())
    })
    return graphData
    
}

export default DataTableView