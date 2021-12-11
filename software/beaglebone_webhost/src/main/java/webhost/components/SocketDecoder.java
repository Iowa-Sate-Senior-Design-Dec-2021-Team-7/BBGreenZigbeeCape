package webhost.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import webhost.WebHostApplication;
import webhost.socket_wrappers.SocketIntentWrapper;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

public class SocketDecoder implements Decoder.Text<SocketIntentWrapper> {

    /**
     * {@code Log} for this controller
     */
    private Log log = LogFactory.getLog(WebHostApplication.class);

    /**
     * {@code ObjectMapper} for this {@code Encoder}
     */
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public SocketIntentWrapper decode(String s) {

        try { return mapper.readValue(s, SocketIntentWrapper.class); }
        catch (IOException e) { log.error("SOCKET: decode exception", e); }

        return null;
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        //custom init
    }

    @Override
    public void destroy() {
        //close resources
    }
}
