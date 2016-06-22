package tests;

import org.testng.annotations.Test;
import pages.LandingPage;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class CodeTester {
    private static String startingUrl = LandingPage.pageUrl;
    private static String discoveredUrl;
    private static String filePathAndName = "src\\main\\java\\output\\validUrls.txt";
    private static HashSet<String> crawledList = new HashSet<String>();
    private static Queue<Integer> toCrawlList = new LinkedList<Integer>();


//    @Test
//    public static void startCrawl() {
//        try {
//            crawlSite(startingUrl);
//        } catch (Exception e) {
//            System.out.println("Failed to initiate Web crawl.");
//        }
//    }
//
//    private static void crawlSite(String url) throws IOException {
//        try {
//            Document doc = Jsoup.connect(url).get();
//            Elements anchors = doc.select("a");
//
//            for (Element anchor : anchors) {
//                discoveredUrl = anchor.attr("abs:href").toLowerCase();
//                if (!toCrawlList.contains(discoveredUrl)
//                        && discoveredUrl.contains(startingUrl)
//                        && discoveredUrl.length() > 1
//                        && !discoveredUrl.contains("@@")) {
////                        System.out.println("discoveredUrl: " + discoveredUrl);
//                    toCrawlList.add(discoveredUrl);
//                }
//            }
//            crawlUrlList(toCrawlList);
//        } catch (Exception e) {
//            System.out.println("No new links found.");
//        }
//    }
//
//    private static void crawlUrlList(Queue<String> queue) throws IOException {
////        File outputFile = new File(filePathAndName);
////        FileWriter fileWriter = new FileWriter(outputFile.getAbsoluteFile());
////        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//        int queueCount = queue.size();
//        String url;
//
////        if (!outputFile.exists()) {
////            outputFile.createNewFile();
////        }
//
//        System.out.println("Queue size: " + queueCount);
//        for (int i = 0; i < queueCount; i++) {
//            url = queue.remove();
//            //System.out.println("Visiting link: " + url);
//            queue.remove(i);
////            System.out.println("Queue Item " + (i + 1) + ": " + url);
//            crawledList.add(url);
////            bufferedWriter.write(url);
//            crawlSite(url);
//        }
//
//        System.out.println("End Count: " + queue.size());
//    }

    @Test
    public static void queueTest() {
        System.out.println("Initial Queue size: " + toCrawlList.size());
        for (int i = 0; i < 10; i++) {
            toCrawlList.add(i);
        }
        for (int number : toCrawlList) {
            System.out.println("Queue index: " + number);
        }
        int queueSize = toCrawlList.size();
        System.out.println("Queue size before: " + queueSize);
        for (int y = 0; y < queueSize; y++) {
            System.out.println("y: " + y);
            System.out.println("List size:" + toCrawlList.size());
            toCrawlList.remove(y);
        }
        System.out.println("Queue size after: " + toCrawlList.size());
    }
}

