
package ru.yandex.practicum.sleeptracker.analyzers;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

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
        LocalDate firstNight = getFirstNight(sleepingSessions);
        LocalDate lastNight = getLastNight(sleepingSessions);
        if (firstNight.isAfter(lastNight)) {
            return new SleepAnalysisResult<>(
                    0L,
                    "Количество бессонных ночей"
            );
        }
        long count = firstNight.datesUntil(lastNight.plusDays(1))
                .filter(date -> !hasSleepDuringNight(sleepingSessions, date))
                .count();

        return new SleepAnalysisResult<>(
                count,
                "Количество бессонных ночей"
        );
    }

    private boolean hasSleepDuringNight(
            List<SleepingSession> sessions,
            LocalDate date) {

        LocalDateTime nightStart = date.atStartOfDay();
        LocalDateTime nightEnd = date.atTime(6, 0);

        return sessions.stream()
                .anyMatch(session ->
                        session.getStart().isBefore(nightEnd)
                                && session.getFinish().isAfter(nightStart));
    }

    private LocalDate getFirstNight(List<SleepingSession> sessions) {
        LocalDateTime firstStart = sessions.getFirst().getStart();

        if (firstStart.toLocalTime().isBefore(LocalTime.NOON)) {
            return firstStart.toLocalDate();
        }

        return firstStart.toLocalDate().plusDays(1);
    }

    private LocalDate getLastNight(List<SleepingSession> sessions) {
        LocalDateTime lastFinish = sessions.getLast().getFinish();

        if (lastFinish.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            return lastFinish.toLocalDate().minusDays(1);
        }

        return lastFinish.toLocalDate();
    }
}
