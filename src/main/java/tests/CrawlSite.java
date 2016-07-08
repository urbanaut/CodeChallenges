package tests;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;
import pages.LandingPage;
import util.FindBrokenImages;
import util.TestBase;
import util.WriteToFile;

import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bill.witt on 6/20/2016.
 */
public class CrawlSite extends TestBase {

    private static boolean showInBrowser;
    private static boolean getPageText;
    private static boolean checkImages;
    private static String startingUrl;
    private static String validUrls;
    private static String extractedText;

    private static HashSet<String> crawledList;
    private static HashSet<String> imageList;
    private static Queue<String> toCrawlList;

    CrawlSite() {
        showInBrowser = false;
        startingUrl = LandingPage.pageUrl;
        validUrls = "validUrls_" + getDateTime() + ".txt";
        extractedText = "dictionary_" + getDateTime() + ".txt";
        crawledList = new HashSet<>();
        toCrawlList = new LinkedBlockingQueue<>(1024);
    }

    @Test
    public static void startCrawl(boolean extractText, boolean checkBrokenImgs) {
        try {
            getPageText = extractText;
            checkImages = checkBrokenImgs;
            crawlSite(startingUrl);
        } catch (Exception e) {
            System.out.println("Failed to initiate Web crawl.");
        }
    }

    private static void crawlSite(String url) throws Exception {
        try {
            if (showInBrowser) {
                driver.navigate().to(url);
            }

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
                    WriteToFile.writeOutput(validUrls, "\n[" + toCrawlList.size() + "] " + discoveredUrl);
                }
            }

            if (getPageText) {
                System.out.println("Extracting page text.");
                String pageText = doc.body().text();
                WriteToFile.writeOutput(extractedText, "\n" + pageText + "\n");
            }

            if (checkImages) {
                System.out.print("Checking for broken images...");
                FindBrokenImages.checkImageLinks(url);
            }

            toCrawlList.remove(url);
            System.gc();
        } catch (Exception e) {
            System.out.println("No new links found, skipping page.");
        } finally {
            if (toCrawlList.size() > 1) {
                try {
                    crawlUrlList(toCrawlList);
                } catch (Exception e) {
                    System.out.println("Error: Unable to crawl URL list.");
                }
            }
        }
    }

    private static void crawlUrlList(Queue<String> queue) throws Exception {
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
    }


    @Override
    protected void finalize() throws Throwable {
        try {
            super.finalize();
        } catch (Throwable t) {
            throw t;
        }
    }
}
