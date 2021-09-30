import "@progress/kendo-theme-material/dist/all.css";
import "hammerjs";
import './App.css';
// import React, { Component } from 'react';
import Donut from "./Examples/Donut";

const Graph = () => {
  return (
    <div className="Container Graph">
        <header className="">
            <h1>Build React Graphs The Easy Way</h1>
        </header>
        <div className="section">
        <Donut />
        </div>
    </div>
  );
}

export default Graph