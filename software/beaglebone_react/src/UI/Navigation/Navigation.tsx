import React from 'react';
import './navigation.css'
 
import { NavLink } from 'react-router-dom';
 
const Navigation = () => {
    return (
       <div className="Header">
          <NavLink style={navStyle} to="/Home">Home</NavLink>
          <NavLink style={navStyle} to="/DataTable">Data</NavLink>
          <NavLink style={navStyle} to="/DeviceTable">Devices</NavLink>
          <NavLink style={navStyle} to="/Settings">Settings</NavLink>
       </div>
    );
}

const navStyle = (props: { isActive: boolean }) => props.isActive ? StylesActive : Styles
const Styles = {
    color : 'aliceblue',
    height : '25px',
    padding : 10,
    borderRadius : 5,
    // textAlign : 'center',
    minHeight : 100,
    textDecoration : 'none', 
    width: '75px',
}
const StylesActive = { ...Styles, ...{
    color : "#59bfff",
}}
 
export default Navigation;