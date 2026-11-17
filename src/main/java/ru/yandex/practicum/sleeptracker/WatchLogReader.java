package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WatchLogReader {

    final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public List<SleepingSession> load(String logPath) {
        List<SleepingSession> sessions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(logPath, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {

                sessions.add(parceLine(line.split(";")));

            }
        } catch (IOException e) {
            System.out.println("ошибка");
        }
        return sessions;
    }

    private SleepingSession parceLine(String[] sleepData) {
        return new SleepingSession(
                LocalDateTime.parse(sleepData[0], DATE_TIME_FORMATTER),
                LocalDateTime.parse(sleepData[1], DATE_TIME_FORMATTER),
                sleepData[2]
        );
    }
}
