package challenge01.pages;

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

    // Tabs
    private static By planTab = By.xpath("//a[@title='Plan Your Trip']");
    private static By resortsTab = By.xpath("//a[@title='Resorts & Snow']");
    private static By storiesTab = By.xpath("//a[@title='Stories']");
    private static By dealsTab = By.xpath("//a[@title='Stories']");
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
            System.out.println("Page validation text found.");
        Assert.assertTrue(pageTitle.contains(validationText), "Verification text did not match page title.");
    }

    public static void clickTab(Tabs tab) {
        switch (tab) {
            case PLAN_YOUR_TRIP:
                WebElement planTabElem = driver.findElement(planTab);
                if (planTabElem.isDisplayed())
                    planTabElem.click();
                break;
            case RESORTS_AND_SNOW:
                WebElement resortsTabElem = driver.findElement(resortsTab);
                if (resortsTabElem.isDisplayed())
                    resortsTabElem.click();
                break;
            case STORIES:
                WebElement storiesTabElem = driver.findElement(storiesTab);
                if (storiesTabElem.isDisplayed())
                    storiesTabElem.click();
                break;
            case DEALS:
                WebElement dealsTabElem = driver.findElement(dealsTab);
                if (dealsTabElem.isDisplayed())
                    dealsTabElem.click();
                break;
            case PASSES:
                WebElement passesTabElem = driver.findElement(passesTab);
                if (passesTabElem.isDisplayed())
                    passesTabElem.click();
                break;
            case EXPLORE:
                WebElement exploreTabElem = driver.findElement(exploreTab);
                if (exploreTabElem.isDisplayed())
                    exploreTabElem.click();
                break;
            default:
                System.out.println("Invalid tab selected.");
        }
    }


}
