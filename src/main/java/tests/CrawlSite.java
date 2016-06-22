package tests;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;
import pages.LandingPage;
import util.TestBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by bill.witt on 6/20/2016.
 */
public class CrawlSite extends TestBase {

    private static String startingUrl = LandingPage.pageUrl;
    private static String discoveredUrl;
    private static String filePathAndName = "src\\main\\java\\output\\validUrls.txt";
    private static HashSet<String> crawledList = new HashSet<String>();
    private static Queue<String> toCrawlList = new LinkedList<String>();
    private static int countdown = 0;


    @Test
    public static void startCrawl() {
        try {
            crawlSite(startingUrl);
        } catch (Exception e) {
            System.out.println("Failed to initiate Web crawl.");
        }
    }

    private static void crawlSite(String url) throws IOException {
        try {
            File outputFile = new File(filePathAndName);
            FileWriter fileWriter = new FileWriter(outputFile.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Document doc = Jsoup.connect(url).get();
            Elements anchors = doc.select("a");

            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }

            for (Element anchor : anchors) {
                discoveredUrl = anchor.attr("abs:href").toLowerCase();
                if (!toCrawlList.contains(discoveredUrl)
                        && discoveredUrl.contains(startingUrl)
                        && discoveredUrl.length() > 1
                        && !discoveredUrl.contains("@@")
                        && !discoveredUrl.contains("&")) {
                        System.out.println("New URL found: " + discoveredUrl);
                    toCrawlList.add(discoveredUrl);
                    bufferedWriter.write(url);
                } else {
                    continue;
                }
            }
            crawlUrlList(toCrawlList);
        } catch (Exception e) {
            System.out.println("No new links found on page.");
        }
    }

    private static void crawlUrlList(Queue<String> queue) throws IOException {
        int queueCount = queue.size();
        String url;

        System.out.println("URLs to visit: " + queueCount);
        for (int i = 0; i < queueCount; i++) {
            url = queue.remove();
            System.out.println("Visiting link: " + url);
            queue.remove(i);
            crawledList.add(url);
            crawlSite(url);
            System.out.println("Visited URLs: " + crawledList.size());

        }
        System.out.println("Final queue count: " + queue.size());
    }
}

