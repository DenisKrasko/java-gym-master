package ru.yandex.practicum.gym;

import java.util.Comparator;
import java.util.Objects;

public class CounterOfTrainings {
    private int countTrainings;
    private Coach coach;

    public CounterOfTrainings(Coach coach) {
        this.coach = coach;
        countTrainings = 1;
    }

    public CounterOfTrainings(Coach coach, int countTrainings) {
        this.countTrainings = countTrainings;
        this.coach = coach;
    }

    public int getCountTrainings() {
        return countTrainings;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCountTrainings(int countTrainings) {
        this.countTrainings = countTrainings;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CounterOfTrainings that = (CounterOfTrainings) o;
        return Objects.equals(coach, that.coach);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coach);
    }
}

class CounterOfTrainingsNameComparator implements Comparator<CounterOfTrainings> {
    @Override
    public int compare(CounterOfTrainings c1, CounterOfTrainings c2) {
        if (c2.getCountTrainings() != c1.getCountTrainings()) {
            return c2.getCountTrainings() - c1.getCountTrainings();
        } else {
            return c2.getCoach().compareTo(c1.getCoach());
        }
    }
}
