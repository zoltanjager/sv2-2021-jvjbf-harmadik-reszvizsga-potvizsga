package examinformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ExamService {

    private Map<String, ExamResult> results = new TreeMap<>();
    private int theoryMax;
    private int practiceMax;


    public void readFromFile(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {

            String line;
            String[] firstLine = br.readLine().split(" ");
            theoryMax = Integer.parseInt(firstLine[0]);
            practiceMax = Integer.parseInt(firstLine[1]);

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String[] anotherParts = parts[1].split(" ");
                String name = parts[0];
                int theoryPoint = Integer.parseInt(anotherParts[0]);
                int practicePoint = Integer.parseInt(anotherParts[1]);

                results.compute(name, (k, v) -> v == null ? new ExamResult(theoryPoint, practicePoint) : null);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read file: " + e.getMessage());
        }
    }


    public Map<String, ExamResult> getResults() {
        return results;
    }

    public int getTheoryMax() {
        return theoryMax;
    }

    public int getPracticeMax() {
        return practiceMax;
    }

    public List<String> findPeopleFailed() {
        return results.entrySet()
                .stream()
                .filter(e -> e.getValue().getTheory() < theoryMax * 0.51 ||
                        e.getValue().getPractice() < practiceMax * 0.51)
                .map(Map.Entry::getKey)
                .toList();

    }

    public String findBestPerson() {
        Map<String, ExamResult> notFailed = new TreeMap<>(results);
        List<String> failedPeople = findPeopleFailed();
        for (String actual : failedPeople) {
            notFailed.remove(actual);
        }

        return notFailed.entrySet()
                .stream()
                .max(Comparator.comparingInt(value -> value.getValue().getPractice() + value.getValue().getTheory()))
                .map(Map.Entry::getKey)
                .get();

    }
}
