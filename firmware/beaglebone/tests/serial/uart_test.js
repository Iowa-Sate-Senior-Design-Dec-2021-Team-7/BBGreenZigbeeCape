var r_bonescript = require("bonescript");
const r_serialport = require("serialport");
const r_serialport_parser = require("@serialport/parser-readline");

var uart_port = "/dev/ttyO4";
var uart_options = {
    autoOpen: false,
    baudRate: 115200,
    dataBits: 8,
    stopBits: 1,
    parity: 'none',
};

var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

console.log("---BEGIN UART TEST ---");

// create serial port and data parser
const port = new r_serialport(uart_port, uart_options);
const parser = new r_serialport_parser({ delimiter: "\r\n", });

// configure serial port and parser callbacks
port.pipe(parser);
port.on('open', uart_open_cbk);
port.on('error', logErr);
//port.on('data', uart_read_cbk);
parser.on('data', uart_read_cbk);

// open port
port.open();

function uart_open_cbk(err) {
    //check for errors
    if (err) {
        console.log("Error: ", err.message);
    }
    console.log("UART opened, baudrate: " + port.baudRate);
    uart_write_cbk();
}

async function uart_write_cbk() {
    while(1) {

        var buf = Buffer.alloc(1024);
        //buf.write("g");

        //port.write(buf);

        //console.log("wrote to uart");
        await sleep(1000);
    }
}

function uart_read_cbk(data) {
    console.log("Recieved: <" + data + ">");
}

function logErr(err) {
    console.log("Error: ", err);
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
