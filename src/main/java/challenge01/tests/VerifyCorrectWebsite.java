package challenge01.tests;

import org.testng.annotations.Test;
import challenge01.pages.TargetPage;
import util.TestBase;

/**
 * Created by bill.witt on 6/15/2016.
 */
public class VerifyCorrectWebsite extends TestBase {

    @Test
    public static void verifySiteTitle() throws InterruptedException {
        // Navigate to page
        TargetPage.navigateToPage();

        // Get page title and verify
        TargetPage.getPageTitle();

        // Select navigation tab
        TargetPage.clickTab(TargetPage.Tabs.DEALS);

    }
}
