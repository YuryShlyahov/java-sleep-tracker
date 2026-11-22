package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class BadSleepCountAnalyzer implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sleepingSessions) {
        long count = sleepingSessions.stream()
                .filter(session -> session.getSleepQuality().equals(SleepQuality.BAD))
                .count();
        return new SleepAnalysisResult<>(count, "Количество сессий плохого сна");
    }
}
