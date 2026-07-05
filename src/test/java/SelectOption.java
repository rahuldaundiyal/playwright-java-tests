import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectOption {

    @Test
    void selectDropdownOption() {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true));

            Page page = browser.newPage();

            // Navigate to the dropdown page
            page.navigate("https://the-internet.herokuapp.com/dropdown");

            // Select Option 1
            page.selectOption("#dropdown", "1");

            // Verify selected value
            String selectedValue = page.locator("#dropdown").inputValue();
            assertEquals("1", selectedValue);

            System.out.println("Option 1 selected successfully.");

            browser.close();
        }
    }
}