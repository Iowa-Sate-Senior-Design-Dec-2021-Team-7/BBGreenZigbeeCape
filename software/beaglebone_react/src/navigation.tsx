import React from 'react';
 
import { NavLink } from 'react-router-dom';
 
const Navigation = () => {
    return (
       <div className="App-header">
          <NavLink style={navStyle} to="/">START</NavLink>
          <NavLink style={navStyle} to="/home">HOME</NavLink>
          <NavLink style={navStyle} to="/graph">GRAPH</NavLink>
       </div>
    );
}

const navStyle = (props: { isActive: boolean; }) => props.isActive ? StylesActive : Styles
const Styles = {
    color : 'aliceblue',
    height : '25px',
    padding : 10,
    borderRadius : 5,
    textAlign : 'center',
    minHeight : 100,
    textDecoration : 'none', 
}
const StylesActive = { ...navStyle, ...{
    color : "#59bfff",
}}
 
export default Navigation;