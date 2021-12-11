let comp_uart = require('./uart.js');
let comp_socket = require('./socket.js');

init();

function init() {

	// initialize uart config
	comp_uart.uart_open();
    // initialize web socket config
	comp_socket.ws_open();
}
