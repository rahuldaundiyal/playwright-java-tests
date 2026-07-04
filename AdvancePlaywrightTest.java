import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdvancePlaywrightTest {

    @Test
    void testAdvancedInteractions() {
        try (Playwright playwright = Playwright.create()) {
            // 1. Launch browser
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();

            // 2. Navigate to login practice page
            page.navigate("https://the-internet.herokuapp.com/login");

            // 3. ADVANCED FILL
            Locator usernameField = page.locator("input#username");
            usernameField.fill("tomsmith");

            Locator passwordField = page.locator("input#password");
            passwordField.fill("SuperSecretPassword!");

            // 4. CLICK
            page.locator("button[type='submit']").click();

            // 5. ASSERTION
            Locator flashAlert = page.locator("#flash");
            assertThat(flashAlert).containsText("You logged into a secure area!");

            // 6. DROPDOWN NAVIGATION & SELECT
            page.navigate("https://the-internet.herokuapp.com/dropdown");
            
            page.locator("#dropdown").selectOption(new SelectOption().setValue("2"));
            
            Locator selectedOption = page.locator("#dropdown option[selected='selected']");
            assertThat(selectedOption).hasText("Option 2");

            browser.close();
        
        // ❌ MISTAKE HERE: Missing closing brace for try block!
    // ❌ MISTAKE HERE: Missing closing brace for method block!
} // This single brace only closes the class, leaving the other blocks open!