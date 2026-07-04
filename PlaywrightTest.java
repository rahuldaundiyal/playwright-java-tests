import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlaywrightTest {
    @Test
    void testGitHubTitle() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            
            // Navigate to GitHub
            page.navigate("https://github.com");
            
            // Get the page title
            String title = page.title();
            System.out.println("====== BROWSER PAGE TITLE: " + title + " ======");
            
            // Verify the title contains the word GitHub
            assertTrue(title.contains("GitHub"));
            
            browser.close();
        }
    }
}