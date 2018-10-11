package ru.clientserverapps.mirea.serverbackend.domain;

import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class Data {
    public static List<Pet> petList = new ArrayList<Pet>() {{
        add(new Pet(0, 10, "Dog", "That's a dog", new BigDecimal("100.00"),
                "IT IS BEAUTIFUL",
                0));
        add(new Pet(1, 12, "Cat", "That's a cat", new BigDecimal("250.00"),
                "People love cats",
                1));
        add(new Pet(2, 8, "Fish", "That's a fish", new BigDecimal("15.00"),
                "Is it a goldfish?",
                2));
        add(new Pet(3, 1, "Elephant", "That's an elephant", new BigDecimal("0.00"),
                "PLEASE TAKE IT AWAY",
                3));
        add(new Pet(4, 10, "Snail", "That's a snail", new BigDecimal("5.00"),
                "I killed one last summer",
                4));
    }};

    public static List<Stuff> stuffList = new ArrayList<Stuff>() {{
        add(new Stuff(0, "Eda", "Food 4 ur animal", new BigDecimal("10"), 100));
        add(new Stuff(1, "Arbuz", "Watermelon, wanna some KFC?", new BigDecimal("15"), 100));
        add(new Stuff(2, "Toy", "Something to play with", new BigDecimal("100"), 23));

    }};

    public static List<Pet> getPetList() {
        return petList;
    }

    public static List<Stuff> getStuffList() {
        return stuffList;
    }
}
