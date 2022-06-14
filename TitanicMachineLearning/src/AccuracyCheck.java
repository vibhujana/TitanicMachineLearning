import java.util.ArrayList;
import java.util.List;

public class AccuracyCheck {
    public static void main(String[] args) {
        Classifier classifier;
        String prediction = "";


        classifier = new Classifier(9);
        ArrayList<Person> training = DataExtracter.LoadTrainData("train.csv");
        ArrayList<Person> test = DataExtracter.LoadTestData("test.csv", "gender_submission.csv");
        classifier.addTrainingData(training);

        classifier.test(test,0);



    }
}