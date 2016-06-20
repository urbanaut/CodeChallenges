package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestMethods {
    //WebDriver driver = new FirefoxDriver();
    //driver.navigate().to("http://www.google.com");

    private WebDriver driver;

    //    @BeforeMethod
    private void startFF() {
        //File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        //FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        //FirefoxProfile firefoxProfile = new FirefoxProfile();
        //driver = new FirefoxDriver(ffBinary,firefoxProfile);
        driver = new FirefoxDriver();
    }

    //    @AfterMethod
    public void killBrowser() {
        driver.close();
    }

    @Test(enabled = false)
    private void cinemarkSearch(Boolean titleSearchDropdown, WebElement searchControl, String searchString)
    {
        System.out.println("In cimenaSearch");
        // Find the Search dialog at the top of the screen
        if (titleSearchDropdown == true)
        {
            // Perform code to select an item from the title dropdown using searchString
        }

        else
        {
            // Perform code to search in the search dialog text box using searchString
            searchControl.click();
            searchControl.sendKeys(searchString);
            Assert.assertEquals(searchString, searchString);
        }
    }

    @Test
    public void WebpageTest()
    {
        // Start Firefox
        startFF();

        // Open the webpage for Cinemark
        driver.get("http://www.cinemark.com");

        // Locate the Find a Movie By Title dropdown control
        WebElement moviesListDropdown = driver.findElement(By.className("btn-search"));

        // Click the Find a movie By Title dropdown control
        moviesListDropdown.click();

        // Create the moviesList and populate it with all movies from the Fina a Movie By Title dropdown
        List<WebElement> moviesList = driver.findElements(By.xpath(".//*[@id='header']/div[2]/div/div/div/div[1]/div/div/ul/li/a"));

        //    Select moviesSelect = new Select(myDriver.findElement(By.className("drop")));
        //    List<WebElement> moviesList = moviesSelect.getOptions();
        //    System.out.println(moviesList.listIterator())

        // Print value of last movie in moviesList
        System.out.println(moviesList.get(moviesList.size()-1).getText());

        // Print size of moviesList
        System.out.println(moviesList.size());

        // Check value of last movie in list with testng Assert
        String lastMovieValueToCompare = "Central Intelligence";
        String lastMovieValue = moviesList.get(moviesList.size()-1).getText();
        Assert.assertEquals(lastMovieValueToCompare, lastMovieValue);
        Assert.assertEquals(lastMovieValueToCompare, lastMovieValue);

        WebElement searchDialog = driver.findElement(By.id("main_inp1"));

        cinemarkSearch(false, searchDialog, "Rich");

        // Find the submit button for the Search dialog at the top of the screen
        WebElement searchDialogSubmitButton = driver.findElement(By.cssSelector("#main_theatres_search>fieldset>input[src]"));
        searchDialogSubmitButton.click();

        // File myFile = new File("C:\\ROLL 2016 - Contact Information and Initial Deposit.xlsx");
//            try {
//                Workbook myExcelWorkbook = Workbook.getWorkbook(new File("C:/selenium_webdriver/ROLL 2016 - Contact Information and Initial Deposit.xls"));
//                int numSheets = myExcelWorkbook.getNumberOfSheets();
//                System.out.println("Number of sheets = " + numSheets);
//                Sheet mySheet = myExcelWorkbook.getSheet(0);
//                Cell myCell = mySheet.getCell(0, 0);
//                String myContents = myCell.getContents();
//                System.out.print(myContents);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (BiffException e) {
//                e.printStackTrace();
//            }

    }
}

