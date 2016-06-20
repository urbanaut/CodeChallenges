package tests;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;
import pages.LandingPage;
import util.TestBase;

import java.io.IOException;
import java.util.HashSet;

/**
 * Created by bill.witt on 6/20/2016.
 */
public class CrawlSite extends TestBase {

    @Test
    public static void crawlSite() throws IOException {
        String url = LandingPage.pageUrl;
        HashSet<String> hash = new HashSet<String>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements anchors = doc.select("a");
            for (Element anchor : anchors) {
                String discoveredUrl = anchor.attr("abs:href").toLowerCase();
                System.out.println("URL: " + discoveredUrl);
                if (!hash.contains(discoveredUrl)) {
                    hash.add(discoveredUrl);
                }
            }

        } catch (Exception e) {
            System.out.println("Failed to get new links");
        }
    }
}
