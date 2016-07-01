package tests;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;
import pages.LandingPage;
import util.FindBrokenImages;
import util.TestBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bill.witt on 6/20/2016.
 */
public class CrawlSite extends TestBase {

    private static boolean showInBrowser = false;
    private static boolean getPageText;
    private static boolean checkImages;
    private static String startingUrl = LandingPage.pageUrl;
    private static String dateTime = getDateTime();
    private static String validUrls = "validUrls_" + dateTime + ".txt";
    private static String extractedText = "dictionary_" + dateTime + ".txt";
    private static String brokenImages = "brokenImages_" + dateTime + ".txt";

    private static HashSet<String> crawledList = new HashSet<String>();
    private static Queue<String> toCrawlList = new LinkedBlockingQueue<>(1024);
    private static BufferedWriter bufferedWriter;


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
                    writeToFile(validUrls, "\n[" + toCrawlList.size() + "] " + discoveredUrl);
                }
            }

            if (getPageText) {
                String pageText = doc.body().text();
                writeToFile(extractedText, "\n" + pageText + "\n");
            }

            if (checkImages) {
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

    private static void writeToFile(String fileName, String output) throws Exception {
        File outputFile = new File(fileName);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(outputFile.getName(),true);
        bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(output);
        bufferedWriter.flush();
        bufferedWriter.close();
    }


    @Override
    protected void finalize() throws Throwable {
        try {
            bufferedWriter.close();
            super.finalize();
        } catch (Throwable t) {
            throw t;
        }
    }
}
