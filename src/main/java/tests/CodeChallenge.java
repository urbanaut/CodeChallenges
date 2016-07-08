package tests;

import org.testng.annotations.Test;
import pages.AllServicesPage;
import pages.ExploreUtahPage;
import pages.LandingPage;
import pages.SearchResultsPage;
import util.CrawlerOptions;
import util.TestBase;

/**
 * Created by bill.witt on 6/15/2016.
 */
public class CodeChallenge extends TestBase {

    private static CrawlerOptions option;
    private static LandingPage.Tabs tab;
    private static String subMenuItem;
    private static String resortName;
    private static String searchWhat;
    private static String searchByResort;
    private static String searchSubCategory;

    CodeChallenge() {
        option = CrawlerOptions.NONE;
        tab = LandingPage.Tabs.DEALS;
        subMenuItem = "Beginner";
        resortName = "Park City";
        searchWhat = "Ski School";
        searchByResort = "Brighton";
        searchSubCategory = "Family and Kids";
    }

    @Test
    public static void runAutomation() throws InterruptedException {
        boolean text, images;

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
        SearchResultsPage.readAndPostResults();

        // Challenge #6: Crawl every page on the site
            // Use CrawlerOption NONE
        // Challenge #7: Get all page's text from Crawler
            // Use CrawlerOption TEXT
        // Challenge #8: Find all page's broken images
            // Use CrawlerOption IMAGES

        text = Boolean.parseBoolean(CrawlerOptions.selectCrawlerOption(option).get(0));
        images = Boolean.parseBoolean(CrawlerOptions.selectCrawlerOption(option).get(1));
        CrawlSite.startCrawl(text, images);

    }
}
