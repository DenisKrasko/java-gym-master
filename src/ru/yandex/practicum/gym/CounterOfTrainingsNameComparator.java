package ru.yandex.practicum.gym;

import java.util.Comparator;

public class CounterOfTrainingsNameComparator implements Comparator<CounterOfTrainings> {
    @Override
    public int compare(CounterOfTrainings c1, CounterOfTrainings c2) {
        if (c2.getCountTrainings() != c1.getCountTrainings()) {
            return c2.getCountTrainings() - c1.getCountTrainings();
        } else {
            return c2.getCoach().compareTo(c1.getCoach());
        }
    }
}