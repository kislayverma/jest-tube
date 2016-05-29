package com.kislay.textclassifier.model;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerEvaluator;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;

/**
 *
 * @author kislay.verma
 */
public class ReturnIdExtractionModel {

    private static NameFinderME nameFinder;
    private static boolean INITIALIZED = false;
    private static final String onlpModelPath = "/Users/kislay.verma/classifier/return-id-model";
    private static final String trainingDataFilePath = "/Users/kislay.verma/classifier/return-id-extraction-dataset.txt";

    public static void main(String[] args) throws InvalidFormatException,
            IOException {
        System.out.println(ReturnIdExtractionModel.getReturnId(
            "What is return id 700001 status"));
    }

    public static String getReturnId(String input) throws IOException {
        if (!INITIALIZED) {
            init();
        }

        String[] sentences = SentenceDetectionModel.detect(input);
        for (String sentence : sentences) {
            String[] tokens = TokenGenerator.tokenize(sentence);
            Span[] spanArr = nameFinder.find(tokens);
            if (spanArr != null && spanArr.length > 0) {
                StringBuilder b = new StringBuilder();
                for (int i = spanArr[0].getStart(); i < spanArr[0].getEnd(); i++) {
                    b.append(tokens[i]);
                }
                System.out.println("Original return id extracted " + b.toString());
                String finalRetId = b.toString().replaceAll("[^\\d]", "");
                if (!finalRetId.isEmpty()) {
                    return finalRetId;
                }
            }
        }

        return null;
    }

    /**
     * We are storing all training data in a single text file and training data
     * should be in the following format.
     *
     * category_of_data1 data1 category_of_data2 data2 category_of_dataN dataN
     *
     * @throws java.io.IOException
     */
    public static void init() throws IOException {
        Charset charset = Charset.forName("UTF-8");
        ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream(trainingDataFilePath), charset);
        ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);
        OutputStream modelOut = null;
        TokenNameFinderModel model = null;

        try {
            model = NameFinderME.train("en", "returnId", sampleStream, null);
            nameFinder = new NameFinderME(model);

            INITIALIZED = true;
        } finally {
            sampleStream.close();
        }

        try {
            modelOut = new BufferedOutputStream(new FileOutputStream(onlpModelPath));
            model.serialize(modelOut);
        } finally {
            if (modelOut != null) {
                modelOut.close();
            }
        }
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
