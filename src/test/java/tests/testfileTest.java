package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.regex.Pattern;

public class testfileTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            // Instantiate Page Object Model Class
            AssetCategoryPage assetCategoryPage = new AssetCategoryPage(page);

            // Step 1: Navigate and Login
            assetCategoryPage.navigate("https://globalparameters.softtrails.net/");
            assetCategoryPage.login("nehapanwar540@gmail.com", "TEST.SAAS.SOFTTRAILS", "Neha@123");

            // Step 2: Navigate inside Asset Category
            assetCategoryPage.navigateToAssetCategory();

            // Step 3: Set category and submit base workflow
            assetCategoryPage.fillCategoryDetails("climb");

            // Step 4: Complete multi-step bypass setup forms
            assetCategoryPage.configureBypassSettingsStep1();
            assetCategoryPage.configureBypassSettingsStep2();
            assetCategoryPage.configureBypassSettingsStep3();

            // Cleanup session
            context.close();
            browser.close();
        }
    }

    // Corrected Page Object Class
    static class AssetCategoryPage {
        private final Page page;

        AssetCategoryPage(Page page) {
            this.page = page;
        }

        void navigate(String url) {
            page.navigate(url);
        }

        void login(String email, String companyCode, String password) {
            try {
                // Correctly handles the sequential 2-step login process
                page.getByPlaceholder("Enter Email").fill(email);
                page.getByPlaceholder("Enter Company Code").fill(companyCode);
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify Email")).click();
                
                page.getByPlaceholder("Enter Password").fill(password);
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
            } catch (PlaywrightException e) {
                System.err.println("Login failed: " + e.getMessage());
            }
        }

        void navigateToAssetCategory() {
            try {
                page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("Organization Setup")).click();
                page.getByText("Asset Management").click();
                page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Asset Category")).click();
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Movable Asset")).click();
            } catch (PlaywrightException e) {
                System.err.println("Navigation to Asset Category failed: " + e.getMessage());
            }
        }

        void fillCategoryDetails(String category) {
            try {
                page.locator("input[name=\"categoryName\"]").fill(category);
                page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Approval WorkflowSelect Approval Workflow$"))).locator("svg").click();
                page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Atomberg")).click();
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
            } catch (PlaywrightException e) {
                System.err.println("Filling Category Details failed: " + e.getMessage());
            }
        }

        void configureBypassSettingsStep1() {
            try {
                page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Asset Category ApprovalSelect\\.\\.\\.Bypass$"))).getByRole(AriaRole.CHECKBOX).check();
                page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Asset ApprovalSelect\\.\\.\\.Bypass$"))).getByRole(AriaRole.CHECKBOX).check();
                page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Asset Addition AuthoritySelect\\.\\.\\.Bypass$"))).getByRole(AriaRole.CHECKBOX).check();
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next")).click();
            } catch (PlaywrightException e) {
                System.err.println("Step 1 Bypass failed: " + e.getMessage());
            }
        }

        void configureBypassSettingsStep2() {
            try {
                page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Asset Allocation RequestSelect\\.\\.\\.Bypass$"))).getByRole(AriaRole.CHECKBOX).check();
                page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Asset Damage RequestSelect\\.\\.\\.Bypass$"))).getByRole(AriaRole.CHECKBOX).check();
                page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Asset Repair RequestSelect\\.\\.\\.Bypass$"))).getByRole(AriaRole.CHECKBOX).check();
                page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Asset Discard RequestSelect\\.\\.\\.Bypass$"))).getByRole(AriaRole.CHECKBOX).check();
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next")).click();
            } catch (PlaywrightException e) {
                System.err.println("Step 2 Bypass failed: " + e.getMessage());
            }
        }

        void configureBypassSettingsStep3() {
            try {
                page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Asset Allocation ApprovalSelect\\.\\.\\.Bypass$"))).getByRole(AriaRole.CHECKBOX).check();
                page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Asset Damage ApprovalSelect\\.\\.\\.Bypass$"))).getByRole(AriaRole.CHECKBOX).check();
                page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Asset Repair ApprovalSelect\\.\\.\\.Bypass$"))).getByRole(AriaRole.CHECKBOX).check();
                page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Asset Discard ApprovalSelect\\.\\.\\.Bypass$"))).getByRole(AriaRole.CHECKBOX).check();
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).first().click();
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close")).click();
            } catch (PlaywrightException e) {
                System.err.println("Step 3 Bypass failed: " + e.getMessage());
            }
        }
    }
}