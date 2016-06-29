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
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bill.witt on 6/20/2016.
 */
public class CrawlSite extends TestBase {

    private static boolean showInBrowser = false;
    private static String startingUrl = LandingPage.pageUrl;
    private static String dateTime = getDateTime();
    private static String filePathAndName = "validUrls_" + dateTime + ".txt";

    private static HashSet<String> crawledList = new HashSet<String>();
    private static Queue<String> toCrawlList = new LinkedBlockingQueue<>(1024);
    private static BufferedWriter bufferedWriter;


    @Test
    public static void startCrawl() {
        try {
            File outputFile = new File(filePathAndName);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(outputFile.getName(),true);
            bufferedWriter = new BufferedWriter(fileWriter);
            crawlSite(startingUrl);
        } catch (Exception e) {
            System.out.println("Failed to initiate Web crawl.");
        }
    }

    private static void crawlSite(String url) throws IOException {
        try {
            if (showInBrowser)
                driver.navigate().to(url);

            Document doc = Jsoup.connect(url).userAgent("Chrome").get();
            Elements anchors = doc.select("a");

            for (Element anchor : anchors) {
                String discoveredUrl = anchor.attr("abs:href").toLowerCase();
                if (!toCrawlList.contains(discoveredUrl)
                        && !crawledList.contains(discoveredUrl)
                        && discoveredUrl.contains(startingUrl)
                        && discoveredUrl.length() > 1
                        && !discoveredUrl.contains("@@")
                        && !discoveredUrl.contains("&")
                        && !discoveredUrl.contains("?")
                        && !discoveredUrl.contains("mobile")) {
                    System.out.println("New URL found: " + discoveredUrl);
                    toCrawlList.add(discoveredUrl);
                    bufferedWriter.write("\n[" + toCrawlList.size() + "] " + discoveredUrl);
                }
            }
            System.gc();
            toCrawlList.remove(url);
            crawlUrlList(toCrawlList);
        } catch (Exception e) {
            System.out.println("No new links found, skipping page.");
        }
    }

    private static void crawlUrlList(Queue<String> queue) throws IOException {
        int queueCount = queue.size();
        String url;

        System.out.println("URLs to visit: " + queueCount);
        System.out.println("Visited URLs: " + crawledList.size());
        for (int i = 0; i < queueCount; i++) {
            url = queue.poll();
            crawledList.add(url);
            System.out.println("Visiting link: " + url);
            crawlSite(url);
        }
        System.out.println("Final queue count: " + queue.size());
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            bufferedWriter.close();
            driver.close();
            driver.quit();
            super.finalize();
        } catch (Throwable t) {
            throw t;
        }
    }
}

