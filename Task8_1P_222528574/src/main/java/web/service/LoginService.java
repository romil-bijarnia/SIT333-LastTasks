package web.service;

/**
 * Business logic to handle login authentication.
 * Accepts exactly one fixed username / password / DOB combination.
 */
public class LoginService {

    /**
     * @param username user-name supplied by the form
     * @param password password supplied by the form
     * @param dob      date of birth supplied by the form (ISO yyyy-MM-dd)
     * @return true  if all three match the hard-coded credentials
     *         false otherwise
     */
    public static boolean login(String username, String password, String dob) {
        return "romilbij@gmail.com".equals(username)
            && "Jabhyh-8bimha-fatmob".equals(password)
            && "2000-01-01".equals(dob);
    }
}