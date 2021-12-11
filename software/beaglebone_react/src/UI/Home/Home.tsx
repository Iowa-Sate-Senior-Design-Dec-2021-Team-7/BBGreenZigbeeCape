import React from 'react';
import './home.css';
import './../../App.css'

const HomeView = () => {
  return (
    <div className="HomeContainer">
      <div className="HomeBody">
        <h1>Senior Design December 2021 Group 7</h1>
        <h2>IoT Data Hub featuring the BeagleBone Microcontroller and CC1352 from Texas Instruments</h2>
        <h2>Project by: Sean Griffen, Sterling Hulling, Parker Larsen, Taylor Weil</h2>
        <hr className="line"/>
        <h2>About this project:</h2>
        <p>
          This project is centered around turning the BeagleBone Microcontroller into an IoT hub by
          aggregating data from a number of Zigbee end devices. Out custom built zigbee cape is positioned
          on top of the BeagleBone and will create a network for other devices to connect to. Once connected, 
          will periodically report their sensor data to the BeagleBone. For this demonstration we have set up
          this application and a web server to collect the data and display it as an end user might choose to,
          but this is not the limit of the BeagleBone's functionality. Using the foundation of aggregating data 
          on an IoT device, any user may use our open source project to customize the code and the devices to 
          server their specific needs. A practical use-case would be to station sensors around a vehicle and 
          report the data when the vehicle returns to it's home WiFi network.
        </p>
        <h2>About this application:</h2>
        <p>
          This web application is meant to be used an example interface and a demonstraction of
          how data collected from the system can be stored and displayed. The app will connect to an
          external database via a rest API. Information will be continuously pulled and become available
          for presentation. Information on the server's database will be periodically reported from the BeagleBone
          hub. 
        </p>
        <h2>How to use:</h2>
          The app will already be configured to connect to a server, in this case it is set to a server
          on the ISU network. To view data being store on the server, ensure the connection banner is green
          and navigate to one of the informational tab by clicking on Data or Devices. Data will show you
          a history of all packages the BeagleBone has received and logged to the database. 
        <p>
          {""}
        </p>
      </div>
    </div>
  );
}

export default HomeView;