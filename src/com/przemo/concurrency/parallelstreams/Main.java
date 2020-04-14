package com.przemo.concurrency.parallelstreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
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
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("*******************************************");
        System.out.println("From a Directory:\n");
        try {
            Stream<Path> pathStream = Files.list(Paths.get(System.getProperty("user.home")));
            System.out.println("Number of elements (files and folders): " + pathStream.parallel().count());
            System.out.println("*******************************************\n");
            System.out.println("\n");
            pathStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("*******************************************");
        System.out.println("From an Array:\n");
        String[] sa = {"1", "2", "34", "54", "sa"};
        Stream<String> arrayStream = Arrays.stream(sa);
        arrayStream.parallel().forEach(System.out::println);
        System.out.println("*******************************************\n");
        System.out.println("\n");
        arrayStream.close();

        System.out.println("*******************************************");
        System.out.println("Random number generators:\n");
        Random random = new Random();
        DoubleStream doubleStream = random.doubles(10);
        OptionalDouble doubleStreamAvg = doubleStream.parallel().peek(System.out::println).average();
        System.out.println("Double Stream Average: " + doubleStreamAvg);
        System.out.println("*******************************************\n");
        System.out.println("\n");
        doubleStream.close();

        System.out.println("*******************************************");
        System.out.println("Concatenating Streams:\n");
        IntStream stream1 = IntStream.of(1, 2, 3, 4, 5);
        IntStream stream2 = IntStream.of(6, 7, 8, 9, 10);
        System.out.print("Concatenated IntStream: ");
        IntStream.concat(stream1, stream2).map(se -> 2 * se).filter(fse -> fse > 10).forEach(e -> System.out.print(e + " "));
        System.out.println("");
        System.out.println("*******************************************\n");
        System.out.println("\n");
        stream1.close();
        stream2.close();

    }

}