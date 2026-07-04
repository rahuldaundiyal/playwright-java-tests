import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdvancePlaywrightTest {

    @Test
    void testAdvancedInteractions() {
        try (Playwright playwright = Playwright.create()) {
            // 1. Launch browser in non-headless mode locally so you can watch it (it runs headlessly automatically in CI)
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // 2. Navigate to a practice website
            page.navigate("https://the-internet.herokuapp.com/login");

            // 3. ADVANCED FILL: Automatically clear and type into input fields
            Locator usernameField = page.locator("input#username");
            usernameField.fill("tomsmith");

            Locator passwordField = page.locator("input#password");
            passwordField.fill("SuperSecretPassword!");

            // 4. ADVANCED CLICK: Click the login button
            page.locator("button[type='submit']").click();

            // 5. ASSERTION: Verify successful login flash banner is visible
            Locator flashAlert = page.locator("#flash");
            assertThat(flashAlert).containsText("You logged into a secure area!");

            // 6. DROPDOWN NAVIGATION & SELECT: Go to a dropdown page and pick an option
            page.navigate("https://the-internet.herokuapp.com/dropdown");
            
            // Selecting an option by its value attribute
            page.locator("#dropdown").selectOption(new SelectOption().setValue("2"));
            
            // Verify option 2 is selected
            Locator selectedOption = page.locator("#dropdown option[selected='selected']");
            assertThat(selectedOption).hasText("Option 2");

            // 7. SCREENSHOT: Take a screenshot of the final page state for validation
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));

            // Clean up and close
            browser.close();
        }
    }
}