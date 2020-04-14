package com.przemo.concurrency.parallelstreams;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonGenerator {
    public static List<Person> generatePersonList(int size) {
        List<Person> ret = new ArrayList<>();
        String firstNames[] = {"Mary", "Patricia", "Linda"};
        String lastNames[] = {"Smith", "Taylor", "Saylor"};

        Random randomGenerator = new Random();
        for (int i = 0; i < size; i++) {
            ret.add(
                    new Person(
                            i, firstNames[randomGenerator.nextInt(2)],
                            lastNames[randomGenerator.nextInt(2)], Date.from(Instant.now()),
                            randomGenerator.nextInt(100_000), randomGenerator.nextDouble() * 10));
        }
        return ret;
    }
}
