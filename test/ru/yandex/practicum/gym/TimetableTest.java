package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник вернулось одно занятие
        TreeMap<TimeOfDay, TrainingSession> exptectedTrainInMonday = new TreeMap<>();
        exptectedTrainInMonday.put(new TimeOfDay(13, 0), new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0)));
        Assertions.assertEquals(exptectedTrainInMonday,
                timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY),
                "Ошибка. В понедельник должно вовзращаться одно занятие");

        //Проверить, что за вторник не вернулось занятий
        Assertions.assertNull(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY),
                "Ошибка. Во вторник нету занятий");
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        TreeMap<TimeOfDay, TrainingSession> exptectedTrainInMonday = new TreeMap<>();
        exptectedTrainInMonday.put(new TimeOfDay(13, 0), new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0)));
        Assertions.assertEquals(exptectedTrainInMonday,
                timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY),
                "Ошибка. В понедельник должно вовзращаться одно занятие");

        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        Assertions.assertEquals(new TrainingSession(groupChild, coach, DayOfWeek.THURSDAY, new TimeOfDay(13, 0)),
                timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY)
                        .get(timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).firstKey()),
                "Ошибка. В четверг в TreeMap первым должно находится занятие в 13:00");

        Assertions.assertEquals(new TrainingSession(groupAdult, coach, DayOfWeek.THURSDAY, new TimeOfDay(20, 0)),
                timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY)
                        .get(timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).lastKey()),
                "Ошибка. В четверг в TreeMap последним должно находится занятие в 20:00");

        Assertions.assertEquals(2, timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).size(),
                "Ошибка. Количество занятий в четверг должно быть два");

        // Проверить, что за вторник не вернулось занятий
        Assertions.assertNull(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY),
                "Ошибка. Во вторник нету занятий");
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        Assertions.assertEquals(timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13, 0)),
                new TrainingSession(group, coach, DayOfWeek.MONDAY, new TimeOfDay(13, 0)),
                "Ошибка! В LinkedHashMap по ключу понедельник, в значении TreeMap по ключу 13:00 должно быть одно занятие");
        //Проверить, что за понедельник в 14:00 не вернулось занятий
        Assertions.assertNull(timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0)),
                "Ошибка! В LinkedHashMap по ключу понедельник, в значении TreeMap по ключу 14:00 не должно быть занятий");
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessionsInTheSameHour() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Смит", "Вова", "Анусьевич");

        Group groupAdult1 = new Group("Йога для взрослых", Age.ADULT, 42);
        TrainingSession thursdayAdult1TrainingSession = new TrainingSession(groupAdult1, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 1));

        timetable.addNewTrainingSession(thursdayAdult1TrainingSession);

        Group groupAdult = new Group("Йога для взрослых", Age.ADULT, 15);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Йога для детей", Age.CHILD, 55);
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(12, 59));

        timetable.addNewTrainingSession(thursdayChildTrainingSession);

        // Проверяю, что за четверг вернулось три занятия в правильном порядке
        Assertions.assertEquals(new TrainingSession(groupChild, coach, DayOfWeek.THURSDAY, new TimeOfDay(12, 59)),
                timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY)
                        .get(timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).firstKey()),
                "Ошибка. В четверг в TreeMap первым должно находится занятие в 12:59");


        Assertions.assertEquals(new TrainingSession(groupAdult1, coach, DayOfWeek.THURSDAY, new TimeOfDay(13, 1)),
                timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY)
                        .get(timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).lastKey()),
                "Ошибка. В четверг в TreeMap последним должно находится занятие в 13:01");

        Assertions.assertEquals(3, timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).size(),
                "Ошибка. Количество занятий в четверг должно быть три");
    }

    @Test
    void testAddTrainingSessionsForSameDayAndSameTimeMoreThanOne() {
        Timetable timetable = new Timetable();

        Group group = new Group("Йога для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        Group group2 = new Group("Пение для взрослых", Age.ADULT, 5);
        Coach coach2 = new Coach("Зырянов", "Рома", "Анусьевич");
        TrainingSession singleTrainingSession2 = new TrainingSession(group2, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession2);

        //Проверить, что за понедельник в 13:00 вернулось только одно занятие, которое было записано первым
        Assertions.assertEquals(new TrainingSession(group, coach, DayOfWeek.MONDAY, new TimeOfDay(13, 0)),
                timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13, 0)),
                "Ошибка! В LinkedHashMap по ключу понедельник, в значении TreeMap по ключу 13:00 должно быть детское занятие");
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessionsThresholdValues() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Смит", "Вова", "Анусьевич");

        Group groupAdult1 = new Group("Йога для взрослых", Age.ADULT, 42);
        TrainingSession thursdayAdult1TrainingSession = new TrainingSession(groupAdult1, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(23, 59));

        timetable.addNewTrainingSession(thursdayAdult1TrainingSession);

        Group groupAdult = new Group("Йога для взрослых", Age.ADULT, 15);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(0, 1));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Йога для детей", Age.CHILD, 55);
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(0, 59));

        timetable.addNewTrainingSession(thursdayChildTrainingSession);

        // Проверяю, что за четверг вернулось три занятия в правильном порядке
        Assertions.assertEquals(new TrainingSession(groupAdult, coach, DayOfWeek.THURSDAY, new TimeOfDay(0, 1)),
                timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY)
                        .get(timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).firstKey()),
                "Ошибка. В четверг в TreeMap первым должно находится занятие в 00:01");


        Assertions.assertEquals(new TrainingSession(groupAdult1, coach, DayOfWeek.THURSDAY, new TimeOfDay(23, 59)),
                timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY)
                        .get(timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).lastKey()),
                "Ошибка. В четверг в TreeMap последним должно находится занятие в 23:59");

        Assertions.assertEquals(3, timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).size(),
                "Ошибка. Количество занятий в четверг должно быть три");
    }

    @Test
    void testGetCountByCoaches() {
        Timetable timetable = new Timetable();

        Group group3 = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach3 = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group3, coach3,
                DayOfWeek.MONDAY, new TimeOfDay(13, 22));

        timetable.addNewTrainingSession(singleTrainingSession);

        Group group2 = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach2 = new Coach("ВыВасильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession2 = new TrainingSession(group2, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(13, 25));

        timetable.addNewTrainingSession(singleTrainingSession2);

        Coach coach = new Coach("Смит", "Вова", "Анусьевич");

        Group groupAdult1 = new Group("Йога для взрослых", Age.ADULT, 42);
        TrainingSession thursdayAdult1TrainingSession = new TrainingSession(groupAdult1, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(23, 59));

        timetable.addNewTrainingSession(thursdayAdult1TrainingSession);

        Group groupAdult = new Group("Йога для взрослых", Age.ADULT, 15);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(0, 1));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Йога для детей", Age.CHILD, 55);
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(0, 59));

        timetable.addNewTrainingSession(thursdayChildTrainingSession);

        Assertions.assertEquals(35, timetable.getCountByCoaches().size(),"error");
//new CounterOfTrainings(coach, 3)
    }


}
