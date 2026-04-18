package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private Map<DayOfWeek, TreeMap<TimeOfDay, TrainingSession>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        TreeMap<TimeOfDay, TrainingSession> treeMap = new TreeMap<>();
        if (timetable.containsKey(trainingSession.getDayOfWeek())) {
            if (timetable.get(trainingSession.getDayOfWeek())
                    .containsKey(trainingSession.getTimeOfDay())) {
                System.out.println("Ошибка. В этот день и время уже проходит другое занятие. Выберите другой день или время");
            } else {
                treeMap = timetable.get(trainingSession.getDayOfWeek());
                treeMap.put(trainingSession.getTimeOfDay(), trainingSession);
                timetable.put(trainingSession.getDayOfWeek(), treeMap);
            }
        } else {
            treeMap.put(trainingSession.getTimeOfDay(), trainingSession);
            timetable.put(trainingSession.getDayOfWeek(), treeMap);
        }
    }

    public TreeMap<TimeOfDay, TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        return timetable.get(dayOfWeek);
    }

    public TrainingSession getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        return timetable.get(dayOfWeek).get(timeOfDay);
    }

    public TreeMap<CounterOfTrainings, Integer> getCountByCoaches() {
        CounterOfTrainingsNameComparator comp = new CounterOfTrainingsNameComparator();
        TreeMap<CounterOfTrainings, Integer> mapCount = new TreeMap<>(comp);
        for (Map.Entry<DayOfWeek, TreeMap<TimeOfDay, TrainingSession>> entryDay : timetable.entrySet()) {
            for (Map.Entry<TimeOfDay, TrainingSession> entryTime : entryDay.getValue().entrySet()) {
                CounterOfTrainings counterOfTrainings = new CounterOfTrainings(entryTime.getValue().getCoach());
                if (mapCount.containsKey(counterOfTrainings)) {
                    mapCount.put(counterOfTrainings, mapCount.get(counterOfTrainings) + 1);
                } else {
                    mapCount.put(counterOfTrainings, 1);
                }
            }
        }
        for (Map.Entry<CounterOfTrainings, Integer> entry: mapCount.entrySet()) {
            entry.getKey().setCountTrainings(entry.getValue());
        }
        return mapCount;
    }
}



