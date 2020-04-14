package com.przemo.concurrency.parallelstreams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("*******************************************");
        System.out.println("From a Collection:\n");
        List<Person> persons = PersonGenerator.generatePersonList(100_000);
        Stream<Person> personStream = persons.stream();// parallelStream();
        System.out.println("Number of Persons: " + personStream.count());
        System.out.println("*******************************************\n");
        System.out.println("\n");

        System.out.println("*******************************************");
        System.out.println("From a Supplier:\n");
        Supplier<String> supplier = new MySupplier();
        Stream<String> generatorStream = Stream.generate(supplier);
        generatorStream.parallel().limit(10).forEach(System.out::println);
        System.out.println("*******************************************\n");
        System.out.println("\n");

        System.out.println("*******************************************");
        System.out.println("From a predefined set of elements:\n");
        Stream<String> predefinedStream = Stream.of("Jose", "Peter", "John");
        predefinedStream.parallel().forEach(System.out::println);
        System.out.println("*******************************************\n");
        System.out.println("\n");

        System.out.println("*******************************************");
        System.out.println("From a File:\n");
        System.out.println(System.getProperty("user.dir"));
        try (BufferedReader br = new BufferedReader(new FileReader("./data/myfile.dat"));) {
            Stream<String> fileLines = br.lines();
            System.out.println("Number of lines in the file: " + fileLines.parallel().count());
            System.out.println("*******************************************\n");

            System.out.println("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}