package cz.cvut.fel.omo.smartHome.utils;

import java.util.List;
import java.util.Random;

public class RandomListElementPicker {
    public static <T> T pickRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("The list is empty or null.");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(list.size());

        return list.get(randomIndex);
    }
}
