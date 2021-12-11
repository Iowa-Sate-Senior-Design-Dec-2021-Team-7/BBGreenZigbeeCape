const WebSocket = require('ws');
const Http = require('http');
let comp_main = require('./main.js');


// global variables
var g_ws_socket;

// local variables
let requestPrefix = "192.168.7.1:8080/";
let socketId = "sddec_proj07_socketconnect_00";

function ws_open() {
	let url = "ws://" + requestPrefix + "socket/";
	g_ws_socket = new WebSocket(url + socketId);
	console.log("WEBSOCKET opened, url: " + url + socketId);
}

function ws_send(intent, payload) {
    let json = {
        "identifier": socketId,
        "intent": intent,
        "payload": payload
    };

    g_ws_socket.send(JSON.stringify(json));
}

function ws_recieve() {
    g_ws_socket.onmessage = function(event) {
        let msg = JSON.parse(event.data);
        switch (msg.intent) {
            default: // display the msg payload by default
                console.log(msg);
                break;
        }
    }
}

function ws_close() {
    g_ws_socket.close();
	console.log("WEBSOCKET closed");
}

module.exports = { g_ws_socket, ws_open, ws_close, ws_send }
