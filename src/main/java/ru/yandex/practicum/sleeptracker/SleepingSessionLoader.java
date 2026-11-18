package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class SleepingSessionLoader {
    Path path;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public SleepingSessionLoader(String path) {
        this.path = Path.of(path);
    }


    public List<SleepingSession> load() {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .filter(line -> !line.isBlank())
                    .map(SleepingSessionLoader::parceLine)   // строка -> Person
                    .toList();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static SleepingSession parceLine(String line) {
        String[] sleepData = line.split(";");
        return new SleepingSession(
                LocalDateTime.parse(sleepData[0], DATE_TIME_FORMATTER),
                LocalDateTime.parse(sleepData[1], DATE_TIME_FORMATTER),
                SleepQuality.valueOf(sleepData[2])
        );
    }
}
