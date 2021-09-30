import { BrowserRouter, Route, Switch } from 'react-router-dom';
import './App.css';

import Graph from './graph'
import Home from './home'
import Error from './error'
import Navigation from './navigation'

function App() {
  return (
    <BrowserRouter>
        <div className="App">
          <Navigation/>
            <Switch>
             <Route path="/" component={null} exact/>
             <Route path="/Home" component={Home} exact/>
             <Route path="/graph" component={Graph}/>
            <Route component={Error}/>
           </Switch>
        </div> 
      </BrowserRouter>
  );
}

export default App;
