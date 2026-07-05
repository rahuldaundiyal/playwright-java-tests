package src.test.java;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayTest {

    @Test
    void verifyExampleDotComTitle() {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true));

            Page page = browser.newPage();

            // Navigate to the website
            page.navigate("https://example.com");

            // Get the page title
            String title = page.title();

            // Verify the title
            assertTrue(title.contains("Example Domain"));

            System.out.println("Page Title: " + title);
            System.out.println("Playwright test executed successfully.");

            browser.close();
        }
    }
}