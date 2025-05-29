package web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import web.handler.LoginServlet;

public class MyServer {
    private static final int PORT = 8082;
    
    public void start() throws Exception {
        // Create a Jetty server listening on port 8082
        Server server = new Server(PORT);
        // Set up a context handler at the root context
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        // Register the LoginServlet to handle requests at path "/login"
        handler.addServlet(LoginServlet.class, "/login");
        System.out.println("Server starting on port " + PORT + "...");
        server.start();
    }
    
    public static void main(String[] args) throws Exception {
        new MyServer().start();
    }
}
