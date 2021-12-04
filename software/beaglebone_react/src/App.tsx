import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import ContextWrapper from './Context';
import AsyncServer from './Data/Server/AsyncServer';
// import Server from './Data/Server/SpringServer';
import Server from './Data/Server/MockServer';

// import Graph from './graph'
import Home from './home'
import DataTableView from './UI/Table/DataTable';
import DeviceTableView from './UI/Table/DeviceTable';
// import Error from './error'
import Navigation from './UI/Navigation/Navigation';

function App() {
  return (
    <ContextWrapper>
      <BrowserRouter>
        <div className="App">
          <AsyncServer Server={Server}/>
          <Navigation/>
          <Routes>
            <Route path="/" element={null} />
            <Route path="/Home" element={<Home/>} />
            <Route path="/DataTable" element={<DataTableView/>} />
            <Route path="/DeviceTable" element={<DeviceTableView/>} />
            {/* <Route path="/graph" element={<Graph/>}/> */}
            {/* <Route element={Error}/> */}
          </Routes>
        </div> 
      </BrowserRouter>
    </ContextWrapper>
  );
}

export default App;