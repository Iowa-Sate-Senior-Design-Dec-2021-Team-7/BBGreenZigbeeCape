import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import ContextWrapper from './Context';
import AsyncServer from './Data/Server/AsyncServer';

// import Graph from './graph'
import HomeView from './UI/Home/Home'
import DataTableView from './UI/Table/DataTable';
import DeviceTableView from './UI/Table/DeviceTable';
import SettingsView from './UI/Settings/Settings';
// import Error from './error'
import Navigation from './UI/Navigation/Navigation';

function App() {
  return (
    <ContextWrapper>
      <BrowserRouter>
        <div className="App">
          <AsyncServer/>
          <Navigation/>
          <Routes>
            <Route path="/" element={null} />
            <Route path="/Home" element={<HomeView/>} />
            <Route path="/DataTable" element={<DataTableView/>} />
            <Route path="/DeviceTable" element={<DeviceTableView/>} />
            <Route path="/Settings" element={<SettingsView/>} />
          </Routes>
        </div> 
      </BrowserRouter>
    </ContextWrapper>
  );
}

export default App;