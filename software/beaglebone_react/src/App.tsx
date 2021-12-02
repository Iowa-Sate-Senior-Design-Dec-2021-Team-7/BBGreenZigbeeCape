import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';

// import Graph from './graph'
import Home from './home'
// import Error from './error'
import Navigation from './navigation'

function App() {
  return (
    <BrowserRouter>
        <div className="App">
          <Navigation/>
          <Routes>
            <Route path="/" element={null} />
            <Route path="/Home" element={<Home/>} />
            {/* <Route path="/graph" element={<Graph/>}/> */}
            {/* <Route element={Error}/> */}
          </Routes>
        </div> 
      </BrowserRouter>
  );
}

export default App;