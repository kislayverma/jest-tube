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
public class PopulateReturnIdTrainingData {

    private final static String[] tempalateArr = new String[] {
        "My <START:returnId> $$$ <END> status\n",
        "<START:returnId> $$$ <END> status\n",
        "Status of <START:returnId> $$$ <END>\n",
    };

    private static Random rand = new Random();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Loading sentence detection model
        OutputStream modelOut = new BufferedOutputStream(
                new FileOutputStream("/Users/kislay.verma/classifier/return-id-extraction-dataset.txt"));

        for (int i = 0; i < 5000; i++) {
            int orderId = rand.nextInt(200000000);

            for (String sample : tempalateArr) {
                String replacement = "return id " + orderId;
                String newStr = sample.replace("$$$", replacement);
                modelOut.write(newStr.getBytes());

                replacement = "return number " + orderId;
                newStr = sample.replace("$$$", replacement);
                modelOut.write(newStr.getBytes());

                replacement = "return " + orderId;
                newStr = sample.replace("$$$", replacement);
                modelOut.write(newStr.getBytes());

                replacement = "retrun number " + orderId;
                newStr = sample.replace("$$$", replacement);
                modelOut.write(newStr.getBytes());

                replacement = "retrun id " + orderId;
                newStr = sample.replace("$$$", replacement);
                modelOut.write(newStr.getBytes());

                replacement = "retrun " + orderId;
                newStr = sample.replace("$$$", replacement);
                modelOut.write(newStr.getBytes());
            }
        }

        modelOut.close();
    }
}
