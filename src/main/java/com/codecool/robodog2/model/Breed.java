package com.codecool.robodog2.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Breed {
    LABRADOR, BULLDOG, DACHSHUND, PUG, VIZSLA, SPANIEL, GOLDEN_RETRIEVER;

    private static final List<Breed> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Breed randomBreed()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
