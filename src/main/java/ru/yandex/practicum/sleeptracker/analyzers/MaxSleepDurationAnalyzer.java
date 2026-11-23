
package ru.yandex.practicum.sleeptracker.analyzers;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxSleepDurationAnalyzer
        implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sleepingSessions) {

        Duration maxDuration = sleepingSessions.stream()
                .map(session -> Duration.between(
                        session.getStart(),
                        session.getFinish()
                ))
                .max(Duration::compareTo)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Невозможно найти максимальную продолжительность: список пуст"
                ));

        long minutes = maxDuration.toMinutes();

        return new SleepAnalysisResult<>(
                minutes,
                "Максимальная продолжительность сна составляет"
        );
    }
}
