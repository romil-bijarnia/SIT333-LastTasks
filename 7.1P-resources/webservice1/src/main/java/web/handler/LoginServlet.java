package web.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.service.LoginService;

/**
 * HTTP endpoint to handle login requests.
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, ServletException {
        // Forward GET to the same logic as POST
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, ServletException {
        System.out.println("[LoginServlet] POST request received");
        String username = req.getParameter("username");
        String password = req.getParameter("passwd");
        String dob      = req.getParameter("dob");
        System.out.println("Credentials received: " + username + " / " + password + " / " + dob);
        
        // Perform authentication using all three fields
        String loginStatus = "fail";
        if (LoginService.login(username, password, dob)) {
            loginStatus = "success";
        }
        
        // Respond with an HTML page indicating login status
        String htmlResponse = "<html>";
        htmlResponse += "<head><title>" + loginStatus + "</title></head>";
        htmlResponse += "<body><h2>Login status: " + loginStatus + "</h2></body>";
        htmlResponse += "</html>";
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(htmlResponse);
    }
}
