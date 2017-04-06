package tools;

import java.security.Key;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionKeyManager implements ServletContextListener, HttpSessionListener, ServletRequestListener {

    private static final String ATTRIBUTE_NAME = "tools.SessionKeyManager";
    private Map<HttpSession, Key> sessions = new ConcurrentHashMap<HttpSession, Key>();
    Encryption crypt = new Encryption();
    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute(ATTRIBUTE_NAME, this);
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        sessions.remove(event.getSession().getServletContext());
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
    	HttpSession session = event.getSession();
        sessions.put(session, crypt.generateSessionKey());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        // NOOP. No logic needed.
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
       // NOOP. No logic needed. Maybe some future cleanup?
    }

    public static SessionKeyManager getInstance(ServletContext context) {
        return (SessionKeyManager) context.getAttribute(ATTRIBUTE_NAME);
    }

    public Key getSessionKey(HttpSession session) {
        return sessions.get(session);
    }

}