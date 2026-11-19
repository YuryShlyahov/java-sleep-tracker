package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class SleepingSessionLoader {
    private final Path path;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public SleepingSessionLoader(String path) {
        this.path = Path.of(path);
    }


    public List<SleepingSession> load() {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines
                    .filter(line -> !line.isBlank())
                    .map(SleepingSessionLoader::parseLine)
                    .toList();
        } catch (IOException ex) {
            throw new RuntimeException("Не удалось прочитать файл: " + path, ex);
        }
    }

    private static SleepingSession parseLine(String line) {
        String[] sleepData = line.split(";");
        if (sleepData.length != 3) {
            throw new IllegalArgumentException("Некорректная запись сна: " + line);
        }
        return new SleepingSession(
                LocalDateTime.parse(sleepData[0].trim(), DATE_TIME_FORMATTER),
                LocalDateTime.parse(sleepData[1].trim(), DATE_TIME_FORMATTER),
                SleepQuality.valueOf(sleepData[2].trim())
        );
    }
}
