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
    public String welcome() {
        // Show welcome page
        return "view-welcome";
    }

    @GetMapping("/login")
    public String loginView() {
        // Show login page
        return "view-login";
    }

    @PostMapping("/login")
    public RedirectView login(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String username = request.getParameter("username");
        String password = request.getParameter("passwd");
        String dob = request.getParameter("dob");
        RedirectView redirectView;
        if (LoginService.login(username, password, dob)) {
            // Login successful -> go to Question 1
            redirectView = new RedirectView("/q1", true);
        } else {
            // Login failed -> stay on login page and flash error message
            redirectView = new RedirectView("/login", true);
            redirectAttributes.addFlashAttribute("message", "Incorrect credentials.");
        }
        return redirectView;
    }

    @GetMapping("/q1")
    public String q1View() {
        // Show Question 1 page
        return "view-q1";
    }

    @PostMapping("/q1")
    public RedirectView q1(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String number1 = request.getParameter("number1");
        String number2 = request.getParameter("number2");
        String resultUser = request.getParameter("result");
        // Compute Q1 (addition) result using the service
        double calculatedResult = MathQuestionService.q1Addition(number1, number2);
        RedirectView redirectView;
        if (calculatedResult == Double.valueOf(resultUser)) {
            // Correct answer -> go to Question 2
            redirectView = new RedirectView("/q2", true);
        } else {
            // Incorrect answer -> stay on Q1 and flash error
            redirectView = new RedirectView("/q1", true);
            redirectAttributes.addFlashAttribute("message", "Wrong answer, try again.");
        }
        return redirectView;
    }

    @GetMapping("/q2")
    public String q2View() {
        // Show Question 2 page
        return "view-q2";
    }

    @PostMapping("/q2")
    public RedirectView q2(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String number1 = request.getParameter("number1");
        String number2 = request.getParameter("number2");
        String resultUser = request.getParameter("result");
        // Compute Q2 (subtraction) result
        double calculatedResult = MathQuestionService.q2Subtraction(number1, number2);
        RedirectView redirectView;
        if (calculatedResult == Double.valueOf(resultUser)) {
            // Correct answer -> go to Question 3
            redirectView = new RedirectView("/q3", true);
        } else {
            // Incorrect answer -> stay on Q2 and flash error
            redirectView = new RedirectView("/q2", true);
            redirectAttributes.addFlashAttribute("message", "Wrong answer, try again.");
        }
        return redirectView;
    }

    @GetMapping("/q3")
    public String q3View() {
        // Show Question 3 page
        return "view-q3";
    }

    @PostMapping("/q3")
    public RedirectView q3(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String number1 = request.getParameter("number1");
        String number2 = request.getParameter("number2");
        String resultUser = request.getParameter("result");
        RedirectView redirectView;
        try {
            // Compute Q3 (multiplication) result with validation
            double calculatedResult = MathQuestionService.q3Multiplication(number1, number2);
            double userResult = Double.valueOf(resultUser);
            if (calculatedResult == userResult) {
                // Correct answer for Q3 -> quiz complete, redirect to welcome with success message
                redirectView = new RedirectView("/", true);
                redirectAttributes.addFlashAttribute("message", "Congratulations! You have answered all questions correctly.");
            } else {
                // Wrong answer -> stay on Q3 and prompt retry
                redirectView = new RedirectView("/q3", true);
                redirectAttributes.addFlashAttribute("message", "Wrong answer, try again.");
            }
        } catch (NumberFormatException e) {
            // Invalid input (e.g. non-numeric) -> stay on Q3 and flash validation error
            redirectView = new RedirectView("/q3", true);
            redirectAttributes.addFlashAttribute("message", "Please enter valid numbers.");
        }
        return redirectView;
    }
}
