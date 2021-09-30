// JavaScript File

const fetch = require('node-fetch');

request_get();
request_post();

function request_get() {
    
    let fetch_options = {
        
        method: "GET"
    };
    
    fetch("http://192.168.7.1:8080/hello", fetch_options)
        .then(response => response.json())
        .then(response => { 
            request_200(response, "GET"); 
        });
    
    return;
}

function request_post() {
    
    let fetch_payload = {
        
        str: "BeagleBone"
    };
    let fetch_options = {
        
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(fetch_payload)
    };
    
    fetch("http://192.168.7.1:8080/hello/name", fetch_options)
        .then(response => response.json())
        .then(response => { 
            request_200(response, "POST");
        });
    
    // let payload = {
        
    //   "str": "BeagleBone"  
    // };
    
    // let xhttp = new XMLHttpRequest();
    // xhttp.onreadystatechange = function() {
    //     if (this.readyState === 4) {
    //         if (this.status === 200) { request_200(this, "PUT"); }
    //         else { request_fail(xhttp, "PUT"); }
    //     }
    // };
    
    // xhttp.open("POST", "localhost:8080/hello/name", true);
    // xhttp.setRequestHeader("Content-type", "application/json");
    // xhttp.send(JSON.stringify(payload));
    
    return;
}

function request_200(response, type) {
    
    console.log("Request [" + type + "] success");
    console.log("Response status: " + response.status);
    console.log("Response payload: " + response.payload);
    console.log("Response exception: " + response.exception);
    
    return;
}

function request_fail(response, type) {
    
    console.log("Request [" + type + "] failure");
    console.log("Response payload: " + JSON.stringify(response));
    
    return;
}