import React from 'react';
 
import { NavLink } from 'react-router-dom';
 
const Navigation = () => {
    return (
       <div className="App-header">
          <NavLink style={navStyle} exact activeStyle={activeNavStyle} to="/">START</NavLink>
          <NavLink style={navStyle} exact activeStyle={activeNavStyle} to="/home">HOME</NavLink>
          <NavLink style={navStyle} exact activeStyle={activeNavStyle} to="/graph">GRAPH</NavLink>
       </div>
    );
}

const navStyle = {
    color : 'aliceblue',
    height : '25px',
    padding : 10,
    borderRadius : 5,
    textAlign : 'center',
    minHeight : 100,
    textDecoration : 'none', 
}
const activeNavStyle = {...navStyle, ...{
    color : "#59bfff",
}}
 
export default Navigation;