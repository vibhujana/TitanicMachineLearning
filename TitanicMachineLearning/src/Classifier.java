import com.sun.deploy.security.SelectableSecurityManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Classifier {

    private ArrayList<Person> trainingData;
    private int k;

    public Classifier(int k) {
        trainingData = new ArrayList<>();
        this.k = k;
    }

    public void addTrainingData(ArrayList<Person> people) {
        for (int i = 0; i < people.size(); i++) {
            trainingData.add(people.get(i));
        }
        Collections.shuffle(trainingData);
    }

    public void addTrainingData(Person person) {
        trainingData.add(person);
    }

    public double calculateDistance(Person p1, Person p2) {
        int count = 0;
        for (int i = 0; i < p1.getInfo().length; i++) {
            count += Math.pow(Math.abs(p1.getInfo()[i] - p2.getInfo()[i]), 2);
        }
        return Math.sqrt(count);
    }

    public ArrayList<Distance> getDistances(Person p) {
        ArrayList<Distance> distances = new ArrayList<>();
        for (int i = 0; i < trainingData.size(); i++) {
            distances.add(new Distance(trainingData.get(i), calculateDistance(trainingData.get(i), p)));
        }
        return distances;
    }

    public void sortByDistance(ArrayList<Distance> distances) {
        boolean sorted = false;
        Distance temp;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < distances.size() - 1; i++) {
                Distance d1 = distances.get(i);
                Distance d2 = distances.get(i + 1);
                if (d1.getDistance() > d2.getDistance()) {
                    temp = new Distance(d1.getP(), d1.getDistance());
                    distances.set(i, d2);
                    distances.set(i + 1, temp);
                    sorted = false;
                }
            }
        }

    }


    public String classify(Person p, double n) {
        ArrayList<Distance> distances = getDistances(p);
        sortByDistance(distances);
        double aliveCount = 0;
        double deadCount = 0;
        for (int i = 0; i < k; i++) {
            if (distances.get(i).getP().getStatus().equals("0")) {
                if(i == 0){
                    deadCount += 1;
                }
                deadCount++;
            } else {
                if(i == 0){
                    aliveCount+= 1;
                }
                aliveCount++;
            }
        }


        String status = "";
        if (aliveCount > deadCount) {
            status = "1";

        } else
            status = "0";
        trainingData.add(new Person(status, p.getInfo()));
        return status;
    }

    public void test(ArrayList<Person> test, double n) {
        ArrayList<Person> correct = new ArrayList<>();
        ArrayList<Person> wrong = new ArrayList<>();

        int i = 0;
        for (Person p : test) {
            String predict = classify(p,n);

            System.out.print("#" + (i+1) + " REAL:\t" + p.getStatus() + " predicted:\t" + predict);
            if (predict.equals(p.getStatus())) {
                correct.add(p);
                System.out.print(" Correct ");
            } else {
                wrong.add(p);
                System.out.print(" WRONG ");
            }

            i++;
            System.out.println(" % correct: " + ((double) correct.size() / i));
        }

        System.out.println(correct.size() + " correct out of " + test.size());
        System.out.println(wrong.size() + " wrong out of " + test.size());
        System.out.println("% Error: " + (double) wrong.size() / test.size());
    }
    public void setK(int k1){
        this.k = k1;
    }


}
