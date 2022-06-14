import javax.swing.*;
import java.util.ArrayList;

public class InteractiveTester {
    public static void main(String[] args) {
        Classifier classifier = new Classifier(15);
        ArrayList<Person> training = DataExtracter.LoadTrainData("train.csv");
        classifier.addTrainingData(training);
        training = DataExtracter.LoadTestData("test.csv", "gender_submission.csv");
        classifier.addTrainingData(training);
        Person p = new Person("0",getInfo());
        double n = 1;
        String str = classifier.classify(p,n);
        System.out.println(str);
    }
    public static double[] getInfo(){
        String response =JOptionPane.showInputDialog("what class is your passenger in(1-3)");
        double luxury = Double.parseDouble(response);
        response = JOptionPane.showInputDialog("Boy(1) or Girl(0)");
        double gender = Double.parseDouble(response);
        response = JOptionPane.showInputDialog("age: ");
        double age = Double.parseDouble(response);

        response = JOptionPane.showInputDialog("siblings: ");
        double sib = Double.parseDouble(response);
        response = JOptionPane.showInputDialog("parents and childs: ");
        double parch = Double.parseDouble(response);
        response = JOptionPane.showInputDialog("fare: ");
        double fare = Double.parseDouble(response);
        double[] info = {luxury,gender,age,sib,parch,fare};
        return info;
    }


}
