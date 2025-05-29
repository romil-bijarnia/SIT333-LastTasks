package web.service;

import org.junit.Assert;
import org.junit.Test;

public class LoginServiceUnitTest {

    @Test
    public void testLoginCorrectCredentials() {
        // Correct credentials should return true
        Assert.assertTrue(LoginService.login("ahsan", "ahsan_pass", "1990-01-01"));
    }

    @Test
    public void testLoginIncorrectUsername() {
        // Incorrect username should fail
        Assert.assertFalse(LoginService.login("wrongUser", "ahsan_pass", "1990-01-01"));
    }

    @Test
    public void testLoginIncorrectPassword() {
        // Incorrect password should fail
        Assert.assertFalse(LoginService.login("ahsan", "wrong_pass", "1990-01-01"));
    }

    @Test
    public void testLoginIncorrectDob() {
        // Incorrect DOB should fail
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "2000-01-01"));
    }
}
