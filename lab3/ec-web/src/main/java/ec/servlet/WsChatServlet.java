package ec.servlet;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.jboss.logging.Logger;

@ServerEndpoint(value = "/websocket/chat")
public class WsChatServlet {
	private static final Logger log = Logger.getLogger(WsChatServlet.class);
	private static final String GUEST_PREFIX = "Guest";
	private static final AtomicInteger connectionIds = new AtomicInteger(0);
	private static final Set<WsChatServlet> connections = new CopyOnWriteArraySet<WsChatServlet>();
	private final String nickname;
	private Session session;

	public WsChatServlet() {
		nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
	}

	@OnOpen
	public void start(Session session) {
		this.session = session;
		System.out.println(session.getRequestURI().toString());
		connections.add(this);
		String message = String.format("* %s %s", nickname, "has joined.");
		broadcast(message);
	}

	@OnClose
	public void end() {
		System.out.println(this.toString());
		connections.remove(this);
		String message = String.format("* %s %s", nickname, "has disconnected.");
		broadcast(message);
	}

	@OnMessage
	public void incoming(String message) {
		System.out.println(message);
		String filteredMessage = String.format("%s: %s", nickname, filter(message.toString()));
		broadcast(filteredMessage);
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		//log.error("Chat Error: " + t.toString(), t);
	}

	private static void broadcast(String msg) {
		for (WsChatServlet client : connections) {
			try {
				synchronized (client) {
					client.session.getBasicRemote().sendText(msg);
				}
			} catch (IOException e) {

				connections.remove(client);
				try {
					client.session.close();
				} catch (IOException e1) {
				}
				String message = String.format("* %s %s", client.nickname, "has been disconnected.");
				broadcast(message);
			}
		}
	}

	public static String filter(String message) {
		if (message == null)
			return (null);

		char content[] = new char[message.length()];
		message.getChars(0, message.length(), content, 0);
		StringBuffer result = new StringBuffer(content.length + 50);
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '<':
				result.append("<");
				break;
			case '>':
				result.append(">");
				break;
			case '&':
				result.append("&");
				break;
			case '"':
				result.append('"');
				break;
			default:
				result.append(content[i]);
			}
		}
		return (result.toString());

	}
}