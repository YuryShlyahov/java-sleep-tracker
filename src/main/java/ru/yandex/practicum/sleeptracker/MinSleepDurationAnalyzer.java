
package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MinSleepDurationAnalyzer
        implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sleepingSessions) {

        Duration minDuration = sleepingSessions.stream()
                .map(session -> Duration.between(
                        session.getStart(),
                        session.getFinish()
                ))
                .min(Duration::compareTo)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Невозможно найти минимальную продолжительность: список пуст"
                ));

        long minutes = minDuration.toMinutes();

        return new SleepAnalysisResult<>(
                minutes,
                "Минимальная продолжительность сна составляет"
        );
    }
}
