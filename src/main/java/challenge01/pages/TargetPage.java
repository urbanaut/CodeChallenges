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
    private static String pageTitle;

    public static void navigateToPage() {
        driver.navigate().to(pageUrl);
    }

    public static void getPageTitle() throws InterruptedException {
        pageTitle = driver.getTitle();
        WebElement titleVerificationElem = driver.findElement(titleVerification);
        if (titleVerificationElem.isEnabled())
            System.out.println("Page validation text found.");
        Assert.assertTrue(pageTitle.contains(validationText), "Verification text did not match page title.");
    }

}
