package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.TestBase;

/**
 * Created by bill.witt on 6/14/2016.
 */
public class TargetPage extends TestBase {

    private static String validationText = "Ski Utah";
    private static By titleVerification = By.xpath("//title[contains(text(),'" + validationText + "')]");
    private static String pageUrl = "https://www.skiutah.com";
    private static String tabValidationText;

    // Tabs
    private static By planTab = By.xpath("//a[@title='Plan Your Trip']");
    private static By resortsTab = By.xpath("//a[@title='Resorts & Snow']");
    private static By storiesTab = By.xpath("//a[@title='Stories']");
    private static By dealsTab = By.xpath("//a[@title='Deals']");
    private static By passesTab = By.xpath("//a[@title='Passes']");
    private static By exploreTab = By.xpath("//a[@title='Explore']");

    public enum Tabs {
        PLAN_YOUR_TRIP,
        RESORTS_AND_SNOW,
        STORIES,
        DEALS,
        PASSES,
        EXPLORE
    }

    public static void navigateToPage() {
        driver.navigate().to(pageUrl);
    }

    public static void getPageTitle() throws InterruptedException {
        String pageTitle = driver.getTitle();
        WebElement titleVerificationElem = driver.findElement(titleVerification);
        if (titleVerificationElem.isEnabled())
            System.out.println("Landing page validation text found.");
        Assert.assertTrue(pageTitle.contains(validationText), "Verification text did not match page title.");
    }

    public static void selectTabbedSection(Tabs tab) {
        String assertionText = clickTab(tab);
        System.out.println("assertionText: " + assertionText);
        WebElement assertionTextElem = driver.findElement(By.xpath("//title[contains(text(),'" + assertionText + "')]"));
        if (assertionTextElem.isEnabled())
            System.out.println("New tab validation text found.");
        Assert.assertTrue(assertionTextElem.isEnabled(), "Assertion Failed: Tab title text not found.");
    }

    private static String clickTab(Tabs tab) {
        switch (tab) {
            case PLAN_YOUR_TRIP:
                WebElement planTabElem = driver.findElement(planTab);
                if (planTabElem.isDisplayed())
                    planTabElem.click();
                tabValidationText = "All Services";
                return tabValidationText;
            case RESORTS_AND_SNOW:
                WebElement resortsTabElem = driver.findElement(resortsTab);
                if (resortsTabElem.isDisplayed())
                    resortsTabElem.click();
                tabValidationText = "Get the Official Utah Snow Report";
                return tabValidationText;
            case STORIES:
                WebElement storiesTabElem = driver.findElement(storiesTab);
                if (storiesTabElem.isDisplayed())
                    storiesTabElem.click();
                tabValidationText = "Read About the Latest Happenings on the Slopes";
                return tabValidationText;
            case DEALS:
                WebElement dealsTabElem = driver.findElement(dealsTab);
                if (dealsTabElem.isDisplayed())
                    dealsTabElem.click();
                tabValidationText = "Ski and Snowboard The Greatest Snow on Earth";
                return tabValidationText;
            case PASSES:
                WebElement passesTabElem = driver.findElement(passesTab);
                if (passesTabElem.isDisplayed())
                    passesTabElem.click();
                tabValidationText = "Season Passes";
                return tabValidationText;
            case EXPLORE:
                WebElement exploreTabElem = driver.findElement(exploreTab);
                if (exploreTabElem.isDisplayed())
                    exploreTabElem.click();
                tabValidationText = "Utah Areas 101";
                return tabValidationText;
            default:
                System.out.println("Invalid tab selected.");
                return null;
        }
    }


}
