package ru.yandex.practicum.sleeptracker;

public class SleepTrackerApp {

    public static void main(String[] args) {
        WatchLogReader watchLogReader = new WatchLogReader();
        System.out.println(watchLogReader.load("src/main/resources/sleep_log.txt"));
    }
}