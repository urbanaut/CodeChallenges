package tests;

import org.testng.annotations.Test;
import pages.AllServicesPage;
import pages.ExploreUtahPage;
import pages.LandingPage;
import util.TestBase;

/**
 * Created by bill.witt on 6/15/2016.
 */
public class VerifyCorrectWebsite extends TestBase {

    private static LandingPage.Tabs tab = LandingPage.Tabs.DEALS;
    private static String subMenuItem = "Beginner";
    private static String resortName = "Park City";
    private static String searchWhat = "Ski School";
    private static String searchByResort = "Brighton";
    private static String searchSubCategory = "Family and Kids";

    @Test
    public static void verifySiteTitle() throws InterruptedException {
        // Navigate to page
        LandingPage.navigateToPage();

        // Challenge #1: Get page title and verify
        LandingPage.getPageTitle();

        // Challenge #2: Select navigation tab
        LandingPage.openTabbedSection(tab);

        // Challenge #3: Navigate through sub-pages and menus
        LandingPage.selectSubMenuItem(tab,subMenuItem);

        // Challenge #4: Return resort travel times from airport
        ExploreUtahPage.compareResortAirportTimes(resortName);

        // Challenge #5: Pass in search parameters
        AllServicesPage.searchForService(searchWhat,searchByResort,searchSubCategory);
    }
}
