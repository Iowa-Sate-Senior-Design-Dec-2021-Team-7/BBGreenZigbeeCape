import React from "react";
import { AppContext } from "../../Context";
import { Data } from "../../Data/DataTypes";
import './chart.css'

const ChartView = () => {
    const {data} = React.useContext(AppContext)

    return (
        <div className="Chart">
            <div className="ChartHead">
                <ChartHeader label="Id"/>
                <ChartHeader label="DataType"/>
                <ChartHeader label="Value"/>           
            </div>
            <div className="ChartBody">
                { data?.map(d => <ChartRow data={d}/> ) }
            </div>
        </div>
    );
}

const ChartHeader = (props: {label: string}) => {
    return (
       <div className="ChartCell">
           <p className="Label">{props.label}</p>
       </div>
    );
}

const ChartRow = (props: {data: Data}) => {
    return (
        <div key={"ChartRow_"+props.data.id} className="ChartRow">
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