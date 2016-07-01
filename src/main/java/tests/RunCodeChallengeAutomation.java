package tests;

import org.testng.annotations.Test;
import pages.AllServicesPage;
import pages.ExploreUtahPage;
import pages.LandingPage;
import pages.SearchResultsPage;
import util.TestBase;

/**
 * Created by bill.witt on 6/15/2016.
 */
public class RunCodeChallengeAutomation extends TestBase {

    private static LandingPage.Tabs tab = LandingPage.Tabs.DEALS;
    private static String subMenuItem = "Beginner";
    private static String resortName = "Park City";
    private static String searchTopic = "Ski School";
    private static String searchByResort = "Brighton";
    private static String searchSubCategory = "Family and Kids";
    private static boolean text;
    private static boolean images;

    @Test
    public static void runAutomation() throws InterruptedException {
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
        AllServicesPage.searchForService(searchTopic,searchByResort,searchSubCategory);
        SearchResultsPage.readAndPostResults();

        // Challenge #6: Crawl every page on the site
        selectCrawlerOption(CrawlerOptions.NONE);

        // Challenge #7: Get all page's text from Crawler
//        selectCrawlerOption(CrawlerOptions.TEXT);

        // Challenge #8: Find all page's broken images
        selectCrawlerOption(CrawlerOptions.IMAGES);

        // Crawl for URLs, text, and images
//        selectCrawlerOption(CrawlerOptions.BOTH);

        // Run Crawler
        CrawlSite.startCrawl(text, images);

    }

    private enum CrawlerOptions {
        NONE,
        TEXT,
        IMAGES,
        BOTH
    }

    private static void selectCrawlerOption(CrawlerOptions option) {
        try {
            switch (option) {
                case NONE:
                    text = false;
                    images = false;
                    break;
                case TEXT:
                    text = true;
                    images = false;
                    break;
                case IMAGES:
                    text = false;
                    images = true;
                    break;
                case BOTH:
                    text = true;
                    images = true;
                    break;
                default:
                    System.out.println("Invalid Crawler option selected.");
            }
        } catch (Exception e) {
            System.out.println("Error: Failed to select a valid crawler option.");
        }
    }
}
