import React from "react";
import { Data, Device } from "../../Data/DataTypes";

export const ChartSettingsRow = (props: {
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

export const ChartHeader = (props: {label: string}) => {
    return (
       <div className="ChartCell">
           <p className="Label">{props.label}</p>
       </div>
    );
}

export const ChartRow = (props: {data: Data | Device, autoScroll?: boolean}) => {
    const fieldRef = React.useRef<HTMLInputElement>(null);
    React.useEffect(() => {
        if (props.autoScroll) fieldRef.current?.scrollIntoView({ behavior: "smooth" })
    })
    const className = "ChartRow" + (props.data.id%2 ? "" : " ChartRowEven")
    
    return (
        <div className={className} ref={fieldRef}>
            {"id" in props.data ? <ChartCell value={props.data.id} /> : null}
            {"dataType" in props.data ? <ChartCell value={props.data.dataType} /> : null}
            {"value" in props.data ? <ChartCell value={props.data.value} /> : null}
            {"deviceType" in props.data ? <ChartCell value={props.data.deviceType} /> : null}
        </div>
    )
}

export const ChartCell = (props: {value: any}) => {
    return (
        <div className="ChartCell">
            <p className="Label">{props.value}</p>
        </div>
    )
}