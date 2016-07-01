package util;

import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;

/**
 * Created by bill.witt on 6/30/2016.
 */
public class FindBrokenImages extends TestBase {

    private static int responseCode;
    private static String dateTime = getDateTime();
    private static String brokenImgFile = "brokenImages_" + dateTime + ".txt";

    private static HashSet<String> brokenImgList = new HashSet<String>();
    private static List<WebElement> imageList;

    public static void checkImageLinks(String url) throws Exception {
        try {
            String imgUrl;
            Jsoup.connect(url).userAgent("Chrome").get();

            imageList = driver.findElements(By.tagName("img"));
            for (WebElement image : imageList) {
                 imgUrl = image.getAttribute("src").trim();
                if (!imgUrl.equals("")) {
                    responseCode = getResponseCode(imgUrl);
                    if (responseCode != 200) {
                        System.out.println("\nResponse Code: " + responseCode);
                        System.out.println("Broken image found at URL: " + imgUrl);
                        brokenImgList.add(imgUrl);
                        WriteToFile.writeOutput(brokenImgFile,imgUrl);
                    }
                }
            }
        } catch (Exception e) {
            if (responseCode == 200) {
                System.out.println("no broken images found.");
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
}
