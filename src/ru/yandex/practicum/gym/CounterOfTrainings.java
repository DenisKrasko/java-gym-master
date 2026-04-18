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