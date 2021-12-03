
import React from "react";
import { Data, DataType } from "./Data/DataTypes";

export interface IContext {
    data: Data[]
    setData: React.Dispatch<React.SetStateAction<Data[]>>
}

const defaultContext: Partial<IContext> = {
    data: [
        {
            id: 0,
            dataType: DataType.TEMP,
            value: 69,
        },
        {
            id: 1,
            dataType: DataType.TEMP,
            value: 69.5,
        },
    ],
}

const AppContext = React.createContext<Partial<IContext>>({});

const ContextWrapper: React.FC = ({ children }) => {

    const [data, setData] = React.useState<Data[]>(defaultContext.data!)

    return <AppContext.Provider value={{data, setData}}>
        {children}
    </AppContext.Provider>
}

export { AppContext }
export default ContextWrapper