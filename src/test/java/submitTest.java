package src.test.java;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class submitTest {

    @Test
    void verifyFormSubmission() {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true));

            Page page = browser.newPage();

            // Open the form page
            page.navigate("https://the-internet.herokuapp.com/login");

            // Enter valid credentials
            page.fill("#username", "tomsmith");
            page.fill("#password", "SuperSecretPassword!");

            // Click the Login button
            page.click("button[type='submit']");

            // Verify successful login
            Locator successMessage = page.locator("#flash");

            assertTrue(successMessage.textContent().contains("You logged into a secure area!"));

            System.out.println("Login submitted successfully.");

            browser.close();
        }
    }
}