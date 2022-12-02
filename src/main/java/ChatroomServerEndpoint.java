import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatroomServerEndpoint/{username}")
public class ChatroomServerEndpoint {
    static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void handleOpen(Session userSession, @PathParam("username") String username) {
        users.add(userSession);

        // Adding the user to the map if they're not there
        String username_val = (String) userSession.getUserProperties().get("username");

        try {
            if (username_val == null) {
                userSession.getUserProperties().put("username", username);
                userSession.getBasicRemote()
                        .sendText(buildJsonMessageData("System", "You are now connected as " + username));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Sending the users to the frontend to display all current users
        Iterator<Session> iter = users.iterator();

        try {
            while (iter.hasNext()) {
                iter.next().getBasicRemote().sendText(buildJsonUsersData());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnMessage
    public void handleMessage(@PathParam("username") String username, String message, Session userSession)
            throws IOException {
        // If the user exists in the set of current users, then we send the message to
        // all users connected
        // Otherwise, we add the user to the set and then say that they're connected
        String username_val = (String) userSession.getUserProperties().get("username");
        Iterator<Session> iter = users.iterator();

        if (username_val == null) {
            userSession.getUserProperties().put("username", username);
            userSession.getBasicRemote()
                    .sendText(buildJsonMessageData("System", "You are now connected as " + username));

            while (iter.hasNext()) {
                iter.next().getBasicRemote().sendText(buildJsonUsersData());
            }
        } else {
            while (iter.hasNext()) {
                iter.next().getBasicRemote().sendText(buildJsonMessageData(username, message));
            }
        }
    }

    @OnClose
    public void handleClose(Session userSession) {
        users.remove(userSession);

        Iterator<Session> iter = users.iterator();

        while (iter.hasNext()) {
            try {
                iter.next().getBasicRemote().sendText(buildJsonUsersData());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private String buildJsonUsersData() {
        Iterator<String> iter = getUsernames().iterator();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        while (iter.hasNext()) {
            jsonArrayBuilder.add((String) iter.next());
        }

        return Json.createObjectBuilder().add("users", jsonArrayBuilder).build().toString();
    }

    private String buildJsonMessageData(String username, String message) {
        JsonObject jsonObject = Json.createObjectBuilder().add("message", username + ": " + message).build();
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.write(jsonObject);
        }
        return stringWriter.toString();
    }

    private Set<String> getUsernames() {
        HashSet<String> result = new HashSet<String>();
        Iterator<Session> iter = users.iterator();

        try {
            while (iter.hasNext()) {
                Object item = iter.next().getUserProperties().get("username");
                if (item != null) {
                    result.add(item.toString());
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }
}
