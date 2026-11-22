
package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AverageSleepDurationAnalyzer
        implements Function<List<SleepingSession>, SleepAnalysisResult<Double>> {

    @Override
    public SleepAnalysisResult<Double> apply(List<SleepingSession> sleepingSessions) {
        double averageDuration = sleepingSessions.stream()
                .mapToLong(session -> Duration.between(
                        session.getStart(),
                        session.getFinish()
                ).toMinutes())
                .average()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Невозможно вычислить среднюю продолжительность: список пуст"
                ));
        return new SleepAnalysisResult<>(
                averageDuration,
                "Средняя продолжительность сна составляет"
        );
    }
}
