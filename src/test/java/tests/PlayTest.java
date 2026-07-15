package tests;

import com.microsoft.playwright.*;
import java.util.HashMap;
import java.util.Map;

public class PlayTest {
    public static void main(String[] args) {
        // Force Playwright to download browsers if they don't exist
        Map<String, String> env = new HashMap<>(System.getenv());
        
        try (Playwright playwright = Playwright.create(new Playwright.CreateOptions().setEnv(env))) {
            System.out.println("Launching Chromium UI...");
            
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));

            Page page = browser.newPage();
            page.navigate("https://google.com");
        

            System.out.println("Page Title: " + page.title());
            
            try { Thread.sleep(3000); } 
            catch (InterruptedException e) { e.printStackTrace(); }

            browser.close();
            System.out.println("successfully ");

        }
    }
}