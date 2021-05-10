package net.playdoh.algo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

//TC - Linear - o(n^n)
//SC - Linear - o(n)
//Purpose - Bubble sort the given input array
public class DuplicateFiles {

    public static void main(String[] args) {

        List<File> files = new ArrayList<>();
        files.add(new File("src/main/resources/banner.jpg"));
        files.add(new File("src/main/resources/banner1.jpg"));
        files.add(new File("src/main/resources/banner2.jpg"));
        files.add(new File("src/main/resources/bootstrap.yml"));
        files.add(new File("src/main/resources/application.yml"));
        files.add(new File("src/main/resources/logback-spring.xml"));

        files.stream().forEach(file -> System.out.println("File ::: " + file.getName()));

        System.out.println("Remove Duplicate File...");

        try {
            final MessageDigest messageDigest  = MessageDigest.getInstance("SHA-512");
            Set<File> uniqueFiles = new HashSet<>(files);
            uniqueFiles.stream().forEach(file -> {
                try {
                    System.out.println("File Hash : " + file.getName() + " :::>>> " + Arrays.toString(messageDigest.digest(Files.readAllBytes(Paths.get(file.getAbsolutePath())))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
