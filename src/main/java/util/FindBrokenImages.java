package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by bill.witt on 6/30/2016.
 */
public class FindBrokenImages extends TestBase {

    private static int responseCode;
    private static List<WebElement> imageList;

    public static void checkImageLinks() throws Exception {
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet request = new HttpGet(url);
//        HttpResponse response = (HttpResponse) client.execute(request);
        System.out.println("Searching for broken images...");
        imageList = driver.findElements(By.tagName("img"));
        for (WebElement image : imageList) {
            if (!image.getAttribute("src").equals("")) {
                responseCode = getResponseCode(image.getAttribute("src").trim());
                if (responseCode != 200) {
                    System.out.println("Broken image found at URL: " + image.getAttribute("src").trim());
                }
            }
        }
    }

    private static int getResponseCode(String url) throws Exception {
        URL u = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        return connection.getResponseCode();
    }









//        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver (driver);
//
//        // Storing all the image elemt  in the variable allImages
//        List<WebElement> allImages = eventFiringWebDriver.findElements(By.tagName("img"));
//        int countBrokenImages = 0;
//
//        // Declaring a dynamic  string of array which will store src of all the broken images
//        List<String> BrokenImageUrl = new ArrayList<String>();
//
//        String script = "return (typeof arguments[0].naturalWidth!=\"undefined\" && arguments[0].naturalWidth>0)";
//
//        for (WebElement image : allImages)
//        {
//            Object imgStatus = eventFiringWebDriver.executeScript(script, image);
//            if(imgStatus.equals(false))
//            {
//                String  currentImageUrl = image.getAttribute("src");
//                String imageUrl = currentImageUrl ;
//                BrokenImageUrl.add(imageUrl);
//                countBrokenImages++;
//            }
//        }
//
//        // Printing the src of the broken images if any
//        System.out.println("Number of broken images found in the page : " +countBrokenImages);
//        for (String z : BrokenImageUrl)
//        {
//            System.out.println(z);
//        }
//    }
}
