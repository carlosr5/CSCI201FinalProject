import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatroomServerEndpoint")
public class ChatroomServerEndpoint {
	static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void handleOpen(Session userSession) {
		users.add(userSession);
	}
	
	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException {
//		If the user exists in the set of current users, then we send the message to all users connected
//		Otherwise, we add the user to the set and then say that they're connected
		String username = (String) userSession.getUserProperties().get("username");
		
		if(username == null) {
			userSession.getUserProperties().put("username", message);
			userSession.getBasicRemote().sendText("System: You are now connected as " + message);
		}
		else {
			Iterator<Session> iter = users.iterator();
			while (iter.hasNext()) {
				iter.next().getBasicRemote().sendText(username + ": " + message + '\n');
			}
		}
	}
	
	@OnClose
	public void handleClose(Session userSession) {
		users.remove(userSession);
	}
}
