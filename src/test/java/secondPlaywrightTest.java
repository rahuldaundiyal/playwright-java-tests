import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class secondPlaywrightTest {

    @Test
    void testAdvancedInteractions() {
        try (Playwright playwright = Playwright.create()) {
            // 1. Launch browser headlessly for CI
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();

            // 2. Navigate to login practice page
            page.navigate("https://the-internet.herokuapp.com/login");

            // 3. ADVANCED FILL: Type credentials
            Locator usernameField = page.locator("input#username");
            usernameField.fill("tomsmith");

            Locator passwordField = page.locator("input#password");
            passwordField.fill("SuperSecretPassword!");

            // 4. CLICK: Click login button
            page.locator("button[type='submit']").click();

            // 5. ASSERTION: Verify success banner is displayed
            Locator flashAlert = page.locator("#flash");
            assertThat(flashAlert).containsText("You logged into a secure area!");

            // 6. DROPDOWN: Navigate and select an option
            page.navigate("https://the-internet.herokuapp.com/dropdown");
            page.locator("#dropdown").selectOption(new SelectOption().setValue("2"));
            
            // Verify option 2 text match
            Locator selectedOption = page.locator("#dropdown option[selected='selected']");
            assertThat(selectedOption).hasText("Option 2");

            browser.close();
        } // Fix 1: Closed the try block
    } // Fix 2: Closed the method block
} // Closed the class block