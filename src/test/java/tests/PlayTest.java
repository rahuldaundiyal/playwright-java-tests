package tests;

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
            page.navigate("https://example.com");

            String title = page.title();
            assertTrue(title.contains("Example Domain"));

            System.out.println("Page Title: " + title);
            System.out.println("Playwright test executed successfully.");

            browser.close();
        }
    }
}