package util;

import org.testng.annotations.Test;

import java.util.Stack;

public class CodeTester {

    private static Stack<Integer> toCrawlList = new Stack<Integer>();


    @Test
    public static void queueTest() {
        System.out.println("Initial Queue size: " + toCrawlList.size());
        for (int i = 0; i < 10; i++) {
            toCrawlList.push(i);
        }
        for (int number : toCrawlList) {
            System.out.println("Queue index: " + number);
        }
        int queueSize = toCrawlList.size();
        System.out.println("Queue size before: " + queueSize);
        for (int y = 0; y < queueSize; y++) {
            System.out.println("y: " + y);
            System.out.println("List size:" + toCrawlList.size());
            toCrawlList.pop();
        }
        System.out.println("Queue size after: " + toCrawlList.size());
    }

}

