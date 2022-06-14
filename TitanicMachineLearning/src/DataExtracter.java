import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;


public class DataExtracter {
    private static final int GENDER = 4;

    public static String readFileAsString(String filepath) {
        ClassLoader classLoader = DataExtracter.class.getClassLoader();
        File file = new File(classLoader.getResource(filepath).getFile());

        // Read File Content
        String content = "";
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND: " + filepath);
            e.printStackTrace();
        }

        return content;
    }

    public static ArrayList<Person> LoadTrainData(String filepath) {
        String data = normalizeLineBreaks(readFileAsString(filepath));
        String[] lines = data.split("\n");
        ArrayList<Person> people = new ArrayList<>();
        for (int a = 1; a < lines.length; a++) {

            String[] allInfo = lines[a].split(",");
            double[] requiredInfo = new double[6];
            int loc = 0;
            for (int i = 2; i <= 10; i++) {
                if (i != 3 && i != 4 && i != 9) {
                    if (allInfo[i].equals("male"))
                        allInfo[i] = "1";
                    if (allInfo[i].equals("female"))
                        allInfo[i] = "0";
                    if (allInfo[i].equals("") || allInfo[i].equals(" "))
                        allInfo[i] = "0";


                    requiredInfo[loc] = Double.parseDouble(allInfo[i]);

                    loc++;

                }


            }

            people.add(new Person((allInfo[1]), requiredInfo));
        }


        return people;
    }

    public static ArrayList<Person> LoadTestData(String filepath1, String filepath2) {
        String testData = normalizeLineBreaks(readFileAsString(filepath1));
        String idData = normalizeLineBreaks(readFileAsString(filepath2));
        String[] tests = testData.split("\n");
        String[] idList = idData.split("\n");
        ArrayList<Person> people = new ArrayList<>();
        String status = "";
        for (int a = 1; a < tests.length; a++) {

            String[] allInfo = tests[a].split(",");
            String[] idInfo = idList[a].split(",");
            double[] requiredInfo = new double[6];
            int loc = 0;
            for (int i = 1; i <= 9; i++) {
                if (i != 2 && i != 3 && i != 8) {
                    if (allInfo[i].equals("male"))
                        allInfo[i] = "1";
                    if (allInfo[i].equals("female"))
                        allInfo[i] = "0";
                    if (allInfo[i].equals("") || allInfo[i].equals(" "))
                        allInfo[i] = "0";


                    requiredInfo[loc] = Double.parseDouble(allInfo[i]);
                    status = idInfo[1];

                    loc++;

                }


            }


            people.add(new Person(status, requiredInfo));
        }


        return people;
    }

    private static String normalizeLineBreaks(String s) {
        s = s.replace('\u00A0', ' '); // remove non-breaking whitespace characters
        s = s.replace('\u2007', ' ');
        s = s.replace('\u202F', ' ');
        s = s.replace('\uFEFF', ' ');

        return s.replace("\r\n", "\n").replace('\r', '\n');
    }

    public double[] ListToArray(ArrayList<Double> list) {
        double[] arr = new double[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
