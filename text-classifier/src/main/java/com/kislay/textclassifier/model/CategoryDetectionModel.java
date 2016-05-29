package com.kislay.textclassifier.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerEvaluator;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

/**
 *
 * @author kislay.verma
 */
public class CategoryDetectionModel {
    private static DocumentCategorizerME classifier;
    private static final String onlpModelPath = "/Users/kislay.verma/classifier/model";
    private static final String trainingDataFilePath = "/Users/kislay.verma/classifier/category-data.txt";
    private static boolean INITIALIZED = false;

    public static void main(String[] args) throws InvalidFormatException, IOException, Exception {
        String content = "Problems when you are unable to pay";
        System.out.println("Predicted category is " + CategoryDetectionModel.getCategory(content));
    }

    public static String getCategory(String input) throws Exception {
        if (!INITIALIZED) {
            init();
        }
        if (input.split(" ").length < 4) {
            throw new Exception("Not enough data for classification");
        }
        double[] classDistribution = classifier.categorize(input);

        return classifier.getBestCategory(classDistribution);
    }

    /**
     * We are storing all training data in a single text file and
     * training data should be in the following format
     * category_of_data1 data1 
     * category_of_data2 data2
     * category_of_dataN dataN
     */
    public static void init() {
        InputStream dataInputStream = null;
        OutputStream onlpModelOutput = null;
        try {
            dataInputStream = new FileInputStream(trainingDataFilePath);
            ObjectStream<String> lineStream = new PlainTextByLineStream(dataInputStream, "UTF-8");
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);
            // Calculate the training model
            DoccatModel classificationModel = DocumentCategorizerME.train("en", sampleStream);
            classifier = new DocumentCategorizerME(classificationModel);

            INITIALIZED = true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        /*
         * Now we are writing the calculated model to a file in order to use the
         * trained classifier in production
         */
//        try {
//            onlpModelOutput = new BufferedOutputStream(new FileOutputStream(onlpModelPath));
//            model.serialize(onlpModelOutput);
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        } finally {
//            if (onlpModelOutput != null) {
//                try {
//                    onlpModelOutput.close();
//                } catch (IOException e) {
//                    System.err.println(e.getMessage());
//                }
//            }
//        }
    }

    /*
     * Now we call the saved model and test it
     * Give it a new text document and the expected category
     */
    public void test(String cat, String text) throws InvalidFormatException,
            IOException {
        String classificationModelFilePath = "/Users/tkumara/Desktop/model";
        InputStream is = new FileInputStream(classificationModelFilePath);
        DoccatModel classificationModel = new DoccatModel(is);
        DocumentCategorizerME classificationME = new DocumentCategorizerME(classificationModel);
        DocumentCategorizerEvaluator modelEvaluator = new DocumentCategorizerEvaluator(
                classificationME);
        String expectedDocumentCategory = cat;
        String documentContent = text;
        DocumentSample sample = new DocumentSample(expectedDocumentCategory,
                documentContent);
        double[] classDistribution = classificationME.categorize(documentContent);
        String predictedCategory = classificationME.getBestCategory(classDistribution);
        modelEvaluator.evaluteSample(sample);
        double result = modelEvaluator.getAccuracy();
        System.out.println("Model prediction : " + predictedCategory);
        System.out.println("Accuracy : " + result);
    }
}
