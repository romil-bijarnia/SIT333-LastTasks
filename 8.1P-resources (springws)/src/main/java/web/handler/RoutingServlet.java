package web.handler;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import web.service.LoginService;
import web.service.MathQuestionService;

@Controller
@RequestMapping("/")
public class RoutingServlet {

    @GetMapping("/")
    public String welcomeView(HttpServletRequest request) {
        // Retrieve any flash message (e.g., final congratulations) and pass to view
        String message = (String) request.getSession().getAttribute("message");
        request.setAttribute("message", message);
        request.getSession().removeAttribute("message");
        System.out.println("[RoutingServlet] Showing welcome page");
        return "view-welcome";
    }

    @GetMapping("/login")
    public String loginView(HttpServletRequest request) {
        // Retrieve flash message for login errors (if any)
        String message = (String) request.getSession().getAttribute("message");
        request.setAttribute("message", message);
        request.getSession().removeAttribute("message");
        System.out.println("[RoutingServlet] Showing login page");
        return "view-login";
    }

    @PostMapping("/login")
    public RedirectView login(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        System.out.println("[RoutingServlet] Processing login form...");
        String username = request.getParameter("username");
        String password = request.getParameter("passwd");
        String dob      = request.getParameter("dob");
        System.out.println("Login attempt: " + username + " / " + password + " / " + dob);
        // Check credentials
        if (LoginService.login(username, password, dob)) {
            // Success: proceed to Question 1
            return new RedirectView("/q1", true);
        } else {
            // Failure: redirect back to login with error message
            redirectAttrs.addFlashAttribute("message", "Incorrect credentials. Please try again.");
            return new RedirectView("/login", true);
        }
    }

    @GetMapping("/q1")
    public String q1View(HttpServletRequest request) {
        // Show the addition question page (Q1). If there's a message (error) in session, move it to request.
        String message = (String) request.getSession().getAttribute("message");
        request.setAttribute("message", message);
        request.getSession().removeAttribute("message");
        System.out.println("[RoutingServlet] Showing Q1 page");
        return "view-q1";
    }

    @PostMapping("/q1")
    public RedirectView q1Submit(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        System.out.println("[RoutingServlet] Processing Q1 form...");
        String num1 = request.getParameter("number1");
        String num2 = request.getParameter("number2");
        String resultInput = request.getParameter("result");
        // Validate input presence
        if (isInvalidInput(num1, num2, resultInput)) {
            redirectAttrs.addFlashAttribute("message", "Please enter valid numbers for the calculation.");
            return new RedirectView("/q1", true);
        }
        try {
            double expected = MathQuestionService.q1Addition(num1, num2);
            System.out.println("Q1: " + num1 + " + " + num2 + " = " + expected + ", user answered " + resultInput);
            if (expected == Double.valueOf(resultInput)) {
                // Correct answer -> go to next question (Q2)
                return new RedirectView("/q2", true);
            } else {
                // Wrong answer -> stay on Q1 with error message
                redirectAttrs.addFlashAttribute("message", "Wrong answer, try again.");
                return new RedirectView("/q1", true);
            }
        } catch (NumberFormatException e) {
            // If parsing the numbers fails
            redirectAttrs.addFlashAttribute("message", "Invalid input. Please enter valid numbers.");
            return new RedirectView("/q1", true);
        }
    }

    @GetMapping("/q2")
    public String q2View(HttpServletRequest request) {
        // Show the subtraction question page
        String message = (String) request.getSession().getAttribute("message");
        request.setAttribute("message", message);
        request.getSession().removeAttribute("message");
        System.out.println("[RoutingServlet] Showing Q2 page");
        return "view-q2";
    }

    @PostMapping("/q2")
    public RedirectView q2Submit(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        System.out.println("[RoutingServlet] Processing Q2 form...");
        String num1 = request.getParameter("number1");
        String num2 = request.getParameter("number2");
        String resultInput = request.getParameter("result");
        if (isInvalidInput(num1, num2, resultInput)) {
            redirectAttrs.addFlashAttribute("message", "Please enter valid numbers for the calculation.");
            return new RedirectView("/q2", true);
        }
        try {
            double expected = MathQuestionService.q2Subtraction(num1, num2);
            System.out.println("Q2: " + num1 + " - " + num2 + " = " + expected + ", user answered " + resultInput);
            if (expected == Double.valueOf(resultInput)) {
                // Correct -> proceed to Q3
                return new RedirectView("/q3", true);
            } else {
                redirectAttrs.addFlashAttribute("message", "Wrong answer, try again.");
                return new RedirectView("/q2", true);
            }
        } catch (NumberFormatException e) {
            redirectAttrs.addFlashAttribute("message", "Invalid input. Please enter valid numbers.");
            return new RedirectView("/q2", true);
        }
    }

    @GetMapping("/q3")
    public String q3View(HttpServletRequest request) {
        // Show the multiplication question page
        String message = (String) request.getSession().getAttribute("message");
        request.setAttribute("message", message);
        request.getSession().removeAttribute("message");
        System.out.println("[RoutingServlet] Showing Q3 page");
        return "view-q3";
    }

    @PostMapping("/q3")
    public RedirectView q3Submit(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        System.out.println("[RoutingServlet] Processing Q3 form...");
        String num1 = request.getParameter("number1");
        String num2 = request.getParameter("number2");
        String resultInput = request.getParameter("result");
        if (isInvalidInput(num1, num2, resultInput)) {
            redirectAttrs.addFlashAttribute("message", "Please enter valid numbers for the calculation.");
            return new RedirectView("/q3", true);
        }
        try {
            double expected = MathQuestionService.q3Multiplication(num1, num2);
            System.out.println("Q3: " + num1 + " * " + num2 + " = " + expected + ", user answered " + resultInput);
            if (expected == Double.valueOf(resultInput)) {
                // Correct final answer -> quiz complete
                redirectAttrs.addFlashAttribute("message", "Congratulations! You’ve completed the quiz.");
                return new RedirectView("/", true);  // Redirect to welcome page
            } else {
                redirectAttrs.addFlashAttribute("message", "Wrong answer, try again.");
                return new RedirectView("/q3", true);
            }
        } catch (NumberFormatException e) {
            redirectAttrs.addFlashAttribute("message", "Invalid input. Please enter valid numbers.");
            return new RedirectView("/q3", true);
        }
    }

    // Helper method to check if any input field is empty/null
    private boolean isInvalidInput(String n1, String n2, String res) {
        return (n1 == null || n1.isEmpty() || n2 == null || n2.isEmpty() 
                || res == null || res.isEmpty());
    }
}
