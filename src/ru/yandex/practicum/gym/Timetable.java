package ru.yandex.practicum.gym;

import com.sun.source.tree.Tree;

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

    public TreeSet<CounterOfTrainings> getCountByCoaches() {
        class CountTrainsComparator implements Comparator<CounterOfTrainings> {
            @Override
            public int compare(CounterOfTrainings i1, CounterOfTrainings i2) {
                if (i1.getCoach().equals(i2.getCoach())) {
                    return 0;
                } else {
                    return i2.getCountTrainings() - i1.getCountTrainings();
                }
            }
        }

        CountTrainsComparator comparator = new CountTrainsComparator();

        ArrayList<CounterOfTrainings> aray = new ArrayList<>();
        //TreeMap<CounterOfTrainings, Integer> countTraining = new TreeMap<>(comparator);
        for (Map.Entry<DayOfWeek, TreeMap<TimeOfDay, TrainingSession>> entryDay : timetable.entrySet()) {
            for (Map.Entry<TimeOfDay, TrainingSession> entryTime : entryDay.getValue().entrySet()) {
                for (CounterOfTrainings counterOfTrainings: aray) {
                    if (counterOfTrainings.getCoach().equals(entryTime.getValue().getCoach())) {

                    }
                    for (Map.Entry<Integer, CounterOfTrainings> sss: map.entrySet()) {
                        if (sss.getValue().getCoach().equals(entryTime.getValue().getCoach())) {

                            counterOfTrainings.setCountTrainings(counterOfTrainings.getCountTrainings() + 1);
                        } else {
                            map.put(1, new CounterOfTrainings(entryTime.getValue().getCoach()));
                        }
                    }
                }
                if (entryTime.getValue().getCoach())) {

                    countTraining.add(entryTime.getValue().getCoach()), countTraining()counterOfTrainings.setCountTrainings(counterOfTrainings.getCountTrainings() + 1);
                } else {
                    countTraining.add(new CounterOfTrainings(entryTime.getValue().getCoach()));
                }
                countTraining.add(new CounterOfTrainings(entryTime.getValue().getCoach()));
                for (CounterOfTrainings counterOfTrainings : countTraining) {
                    if (counterOfTrainings.getCoach().equals(entryTime.getValue().getCoach())) {
                        //counterOfTrainings.setCountTrainings(counterOfTrainings.getCountTrainings() + 1);
                        //countTraining.add(new CounterOfTrainings(entryTime.getValue().getCoach()));
                        //countTraining.add(new CounterOfTrainings(entryTime.getValue().getCoach()));
                    } else {
                        //countTraining.add(new CounterOfTrainings(entryTime.getValue().getCoach()));
                        //countTraining.add(new CounterOfTrainings(entryTime.getValue().getCoach()));
                        //counterOfTrainings.setCountTrainings(counterOfTrainings.getCountTrainings() + 1);
                    }
                }
            }
        }
        return countTraining;
    }

}



