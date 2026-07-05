
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectOption {

    @Test
    void testDropdownSelection() {
        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true));

            Page page = browser.newPage();

            // Open the dropdown page
            page.navigate("https://the-internet.herokuapp.com/dropdown");

            // Locate the dropdown
            Locator dropdown = page.locator("#dropdown");

            // Select "Option 2"
            dropdown.selectOption(new SelectOption().setLabel("Option 2"));

            // Verify the selected value
            String selectedValue = dropdown.inputValue();
            assertEquals("2", selectedValue);

            System.out.println("Dropdown selection verified successfully!");

            browser.close();
        }
    }
}