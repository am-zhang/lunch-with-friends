package CSCI201_LunchWithFriends;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.*;

public class ChatConfigurator extends ServerEndpointConfig.Configurator{
	//override the modify handshake method
	public void modifyHandshake(ServerEndpointConfig c, HandshakeRequest request, HandshakeResponse response)
	{
		c.getUserProperties().put("userID", (String) ((HttpSession) request.getHttpSession()).getAttribute("userID"));
		c.getUserProperties().put("room", (String) ((HttpSession) request.getHttpSession()).getAttribute("room"));
	}
}