
package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsAnalyzer
        implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult<>(
                    0L,
                    "Количество бессонных ночей"
            );
        }

        LocalDateTime observationStart =
                sleepingSessions.get(0).getStart();

        LocalDateTime observationFinish =
                sleepingSessions.get(sleepingSessions.size() - 1)
                        .getFinish();

        LocalDate firstNight = observationStart.toLocalDate();

        if (observationStart.toLocalTime().isBefore(LocalTime.NOON)) {
            firstNight = firstNight.minusDays(1);
        }

        LocalDate lastNight = observationFinish.toLocalDate();

        if (observationFinish.toLocalTime().isBefore(LocalTime.NOON)) {
            lastNight = lastNight.minusDays(1);
        }
        long count = firstNight.datesUntil(lastNight.plusDays(1))
                .filter(date -> isSleeplessNight(date, sleepingSessions))
                .count();

        return new SleepAnalysisResult<>(
                count,
                "Количество бессонных ночей"
        );
    }

    private boolean isSleeplessNight(
            LocalDate date,
            List<SleepingSession> sleepingSessions
    ) {
        LocalDateTime nightStart = date.plusDays(1).atStartOfDay();
        LocalDateTime nightFinish = date.plusDays(1).atTime(6, 0);

        return sleepingSessions.stream()
                .noneMatch(session ->
                        session.getStart().isBefore(nightFinish)
                                && session.getFinish().isAfter(nightStart)
                );
    }
}
