package com.github.kislayverma.textclassifier.scripts;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 *
 * @author kislay.verma
 */
public class PopulateOrderIdTrainingData {

    private final static String[] tempalateArr = new String[] {
        "My <START:orderId> $$$ <END> has not been delievered yet\n",
        "I want to know the status of my <START:orderId> $$$ <END>\n",
        "What is the status of <START:orderId> $$$ <END>\n",
        "What is <START:orderId> $$$ <END> status\n",
        "I want to return some items from my order <START:orderId> $$$ <END>\n",
        "I want to return <START:orderId> $$$ <END>\n",
        "Return <START:orderId> $$$ <END>\n",
        "How do I return against <START:orderId> $$$ <END>\n",
        "How do I create a return against <START:orderId> $$$ <END>\n",
        "<START:orderId> $$$ <END> is very late. Where is it.\n",
        "Cancel <START:orderId> $$$ <END>\n",
        "<START:orderId> $$$ <END> has to be cancelled\n",
    };

    private static Random rand = new Random();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Loading sentence detection model
        OutputStream modelOut = new BufferedOutputStream(
                new FileOutputStream("/Users/kislay.verma/classifier/order-id-extraction-dataset.txt"));

        for (int i = 0; i < 5000; i++) {
            int orderId = rand.nextInt(200000000);

            for (String sample : tempalateArr) {
                String replacement = "order id " + orderId;
                String newStr = sample.replace("$$$", replacement);
                modelOut.write(newStr.getBytes());

                replacement = "order number " + orderId;
                newStr = sample.replace("$$$", replacement);
                modelOut.write(newStr.getBytes());

                replacement = "order " + orderId;
                newStr = sample.replace("$$$", replacement);
                modelOut.write(newStr.getBytes());
            }
        }

        modelOut.close();
    }
}
