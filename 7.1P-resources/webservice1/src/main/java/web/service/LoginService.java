package web.service;

/**
 * Business logic to handle login authentication.
 */
public class LoginService {
    // Static method returns true for successful login, false otherwise.
    public static boolean login(String username, String password, String dob) {
        // Example fixed credentials for a valid user:
        if ("ahsan".equalsIgnoreCase(username) 
                && "ahsan_pass".equals(password) 
                && "1990-01-01".equals(dob)) {
            return true;
        }
        return false;
    }
}
