package tests;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class smsTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // Navigate to your page (replace with actual URL)
            // page.navigate("https://your-app-url.com/add-asset");

            // 1. Fill Asset Name
            page.getByLabel("Asset Name").fill("MacBook Pro 16");

            // 2. Select Asset Category (React-Select)
            // Locating by the input inside the react-select container
            Locator categoryInput = page.locator("#react-select-8-input");
            categoryInput.click();
            categoryInput.fill("Electric_Appliance");
            page.keyboard().press("Enter");

            // 3. Fill Purchase Date (assuming text input or standard date field)
            page.getByPlaceholder("YYYY-MM-DD").fill("2023-10-27");

            // 4. Fill Original Cost
            page.getByLabel("Original Cost (in Rupees)").fill("150000");

            // 5. Fill Scrap Value
            page.getByLabel("Scrap Value (in Rupees)").fill("20000");

            // 6. Fill Useful Life
            page.getByLabel("Useful Life (in months)").fill("45");

            // 7. Fill Unit Of Measure
            page.getByLabel("Unit Of Measure").fill("Unit");

            // 8. Submit the form
            // Using getByRole for the primary submit button
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add Asset")).click();

            System.out.println("Form filled and submitted successfully!");
            
            // Close browser
            browser.close();
        }
    }
}