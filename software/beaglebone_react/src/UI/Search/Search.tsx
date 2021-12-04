// import React from "react";
import "./search.css";
// import { AppContext, IContext } from "../../Context";

export type SearchViewProps = {
    closeOnClick: (value: boolean) => void,
    minId: number|null,
    setMinId: (value: number|null) => void,
    maxId: number|null,
    setMaxId: (value: number|null) => void,

}

const SearchView = (props: SearchViewProps) => {

    // const context = React.useContext<IContext>(AppContext)

    return (
        <div className={"SearchContainer"} >
            <div className="SearchHeader">
                <div className="SearchHeaderTextLine">
                    <p className="SearchText">Table Settings Overlay</p>
                    <div style={{flex: 1}}/>
                    <p className="SearchText" onClick={() => props.closeOnClick(false)}>Close</p>
                </div>
                <hr className="line"/>
            </div>
            <div className="SearchBody">
                <div className="SearchRow">
                    <p className="SearchText SearchTextLabel">Filter IDs</p>
                    <input value={props.minId??"---"} onChange={(text) => props.setMinId(toNumber(text.target.value))}></input>
                    <p className="SeachText SearchTextTo" >{" TO "}</p>
                    <input value={props.maxId??"---"} onChange={(text) => props.setMaxId(toNumber(text.target.value))}></input>
                </div>
            </div>
            <SearchRow />
        </div>
    )
}

const toNumber = (num: string): number|null => {
    const split = num.split("---")
    let rightSide = split[split.length-1]
    if (isNaN(Number(rightSide))) return null
    return Number(rightSide)
}

const SearchRow = (props: {}): JSX.Element => {
    return (
        <div>

        </div>
    )
}

export default SearchView