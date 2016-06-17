package tests;

import org.testng.annotations.Test;
import pages.TargetPage;
import util.TestBase;

/**
 * Created by bill.witt on 6/15/2016.
 */
public class VerifyCorrectWebsite extends TestBase {

    private static TargetPage.Tabs tab = TargetPage.Tabs.DEALS;
    private static String subMenuItem = "Beginner";

    @Test
    public static void verifySiteTitle() throws InterruptedException {
        // Navigate to page
        TargetPage.navigateToPage();

        // Challenge #1: Get page title and verify
        TargetPage.getPageTitle();

        // Challenge #2: Select navigation tab
        TargetPage.openTabbedSection(tab);

        // Challenge #3: Navigate through sub-pages and menus
        TargetPage.selectSubMenuItem(tab,subMenuItem);
    }
}
