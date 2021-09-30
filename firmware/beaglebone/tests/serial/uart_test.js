var r_bonescript = require("bonescript");
var r_serialport = require("serialport");
var r_serialport_parsers = r_serialport.parsers;

var uart_port = "/dev/ttyO1";
var uart_options = {
    baudRate: 115200
};

var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

console.log("---BEGIN UART TEST ---");

//open port
var port = new r_serialport(uart_port, uart_options);
var parser = new r_serialport_parsers.Readline({ delimiter: "\r\n", });

port.pipe(parser);
port.on("open", uart_open_cbk);
port.on('error', logErr);
parser.on("data", uart_read_cbk);

port.open();

function uart_open_cbk(err) {
    //check for errors
    if (err) {
        console.log("Error: ", err.message)
    }
    console.log("UART opened, baudrate: " + port.baudRate);
    uart_write();
}

async function uart_write() {
    while(true) {
        var toWrite = chars.charAt(Math.floor(Math.random() * chars.length));
        console.log("Writing: <" + toWrite + ">");
        port.write(toWrite);
        await sleep(1000);
    }
}

function uart_read_cbk(data) {
    console.log("Recieved: <" + data + ">");
}

function logErr(err) {
    console.log("Error: ", err.message);
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
