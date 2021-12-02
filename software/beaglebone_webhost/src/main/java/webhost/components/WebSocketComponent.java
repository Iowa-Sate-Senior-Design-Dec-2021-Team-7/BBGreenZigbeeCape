package webhost.components;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webhost.WebHostApplication;
import webhost.configurations.Configurator;
import webhost.http_wrappers.ReturnObjectWrapper;
import webhost.socket_wrappers.SocketIntentWrapper;
import webhost.socket_wrappers.SocketReturnWrapper;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

@Component
@ServerEndpoint(value = "/socket/{payload}", decoders = SocketDecoder.class, encoders = SocketEncoder.class, configurator = Configurator.class)
public class WebSocketComponent {
    
    /* ************************************************* START INSTANCE VARIABLES ************************************************** */
    
    @Autowired
    private GlobalUtils gUtils;
    
    private Map<String, Session> listeners_device;
    
    private Map<Session, String> listeners_session;
    
    private Log log;
    
    /* ************************************************** END INSTANCE VARIABLES *************************************************** */
    
    /* **************************************************** START CONSTRUCTORS ***************************************************** */
    
    public WebSocketComponent() {
        log = LogFactory.getLog(WebHostApplication.class);
        listeners_device = new HashMap<>();
        listeners_session = new HashMap<>();
    }
    
    public WebSocketComponent(GlobalUtils gUtils) {
        this();
        this.gUtils = gUtils;
    }
    
    /* ***************************************************** END CONSTRUCTORS ****************************************************** */
    
    /* ******************************************************* START ON OPEN ******************************************************* */
    
    @OnOpen
    public void socketOpen(Session socketSession, @PathParam("payload") String payload) throws IOException {
        log.debug("SOCKET: client attempting to open new connection");
        
        SocketReturnWrapper<String> intentReturn;
        if (payload.contains("sddec_proj07_socketconnect_")) {
            log.debug("SOCKET: client opened a new connection");
            // socket has a valid token for connection, attempt to connect listener
    
            listeners_device.put(payload, socketSession);
            listeners_session.put(socketSession, payload);
            
            intentReturn = new SocketReturnWrapper<>(
                    200,
                    new ReturnObjectWrapper<>(200, "Socket established", null)
            );
        } else {
            log.debug("SOCKET: client invalid for connection");
            
            intentReturn = new SocketReturnWrapper<>(
                    200,
                    new ReturnObjectWrapper<>(500, new String("Socket invalid"), null)
            );
        }
        
        whisper(intentReturn, socketSession);
    }
    
    /* ******************************************************** END ON OPEN ******************************************************** */
    
    /* ***************************************************** START ON MESSAGE ****************************************************** */
    
    @OnMessage
    public <T> void socketMessage(Session socketSession, SocketIntentWrapper<T> payloadWrap) { this.parseIntent(socketSession, payloadWrap); }
    
    /* ****************************************************** END ON MESSAGE ******************************************************* */
    
    /* ****************************************************** START ON CLOSE ******************************************************* */
    
    @OnClose
    public void socketClose(Session socketSession) {

        String toClose = listeners_session.get(socketSession);
        listeners_device.remove(toClose);
        listeners_session.remove(socketSession);

        log.info("SOCKET: client [" + toClose + "] closed connection");
    }
    
    /* ******************************************************** END ON CLOSE ******************************************************* */
    
    /* ****************************************************** START ON ERROR ******************************************************* */
    
    @OnError
    public void onError(Throwable t) { log.error("SOCKET: error: " + t.getMessage(), t); }
    
    /* ******************************************************** END ON ERROR ******************************************************* */
    
    /* *************************************************** START PRIVATE METHODS *************************************************** */
    
    private <T> void whisperAll(SocketReturnWrapper<T> wrap) {
        for (Entry<Session, String> sessionStringEntry : listeners_session.entrySet()) { this.whisper(wrap, sessionStringEntry.getKey()); }
    }
    
    private <T> void whisper(SocketReturnWrapper<T> wrap, Session to) {
        try { to.getBasicRemote().sendObject(wrap); }
        catch(Exception e) { log.error("SOCKET: whisper exception", e); }
    }

    private <T> void parseIntent(Session whisperBackSession, SocketIntentWrapper<T> intentWrap) {

        switch(intentWrap.getIntent()) {
            
            case 201 :  //
                
                break;
            case 202 :  //
                
                break;
            case 203:   //
                
                break;
            default :   // echo intent payload
                this.echoIntent(whisperBackSession, intentWrap.getPayload().toString(), intentWrap.getIdentifier());
                break;
        }
    }
    
    private void echoIntent(Session whisperBackSession, String toEcho, String echoTo) {
    
        SocketReturnWrapper<String> echo = new SocketReturnWrapper<>(
                201,
                new ReturnObjectWrapper<>(200, new String("ECHO :[" + toEcho + "]"), null)
        );
    
        whisper(echo, whisperBackSession);
    }
    
    /* **************************************************** END PRIVATE METHODS **************************************************** */
    
    /* ************************************************* END WEB SOCKET COMPONENT ************************************************** */
}
