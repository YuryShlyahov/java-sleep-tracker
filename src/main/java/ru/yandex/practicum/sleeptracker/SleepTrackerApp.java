package ru.yandex.practicum.sleeptracker;

import java.util.ArrayList;
import java.util.List;

public class SleepTrackerApp {

    public static void main(String[] args) {
        SleepingSessionLoader sleepingSessionLoader = new SleepingSessionLoader("src/main/resources/sleep_log.txt");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(sleepingSessionLoader.load());
    }
}