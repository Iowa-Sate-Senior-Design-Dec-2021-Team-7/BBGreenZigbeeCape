// required libraries/components
let comp_bonescript = require("bonescript");
let comp_serialport = require("serialport");
let comp_serialport_parser = require("@serialport/parser-readline");
let comp_socket = require('./socket.js');

// global variables
var uart_port;

// local variables
let uart_parser;

let uart_device = "/dev/ttyO4";
let uart_options = {
    autoOpen: false,
    baudRate: 115200,
    dataBits: 8,
    stopBits: 1,
    parity: 'none',
};

function uart_open() {
	uart_port = new comp_serialport(uart_device, uart_options);
	uart_parser = new comp_serialport_parser({ delimiter: "\r\n", });

	// configure serial port and parser callbacks
	uart_port.on('open', uart_open_cbk);
	uart_port.on('error', uart_err_cbk);
	uart_port.pipe(uart_parser);
	uart_parser.on('data', uart_read_cbk);

	// open port
	uart_port.open();
}

function uart_write(str) {
	var buf = new Buffer.alloc(1024);
	buf.write(str);

	uart_port.write(buf);
}

function uart_open_cbk(err) {
    //check for errors
    if (err) { uart_err_cbk(err); }
    console.log("UART opened, baudrate: " + uart_port.baudRate);
}

function uart_read_cbk(devicePayload) {
	if (devicePayload.includes("device_id")) { // jdata is a valid package from the cape
		//console.log("UART recieved valid data: " + devicePayload);
		try {
			devicePayload = JSON.parse(devicePayload);
			let deviceData = {
				device: {
					id_network: devicePayload.device_id,
					type_device: devicePayload.device_type,
					type_data: devicePayload.type,
				},
				type: devicePayload.type,
				value: devicePayload.value,
				timestamp: new Date().toISOString().slice(0, 19),
			};


			comp_socket.ws_send(201, deviceData);
			console.log(JSON.stringify(deviceData));
		} catch (err) {
			console.log("UART parse err: " + JSON.stringify(err));
		}
	}
}

function uart_err_cbk(err) {
    console.log("UART Error: ", err.message);
}

module.exports = { uart_port, uart_parser, uart_write, uart_open }
