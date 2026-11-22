
package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChronotypeAnalyzer
        implements Function<List<SleepingSession>, SleepAnalysisResult<Chronotype>> {

    private static final LocalTime NIGHT_START = LocalTime.of(22, 0);
    private static final LocalTime NIGHT_FINISH = LocalTime.of(6, 0);

    @Override
    public SleepAnalysisResult<Chronotype> apply(
            List<SleepingSession> sleepingSessions
    ) {
        Map<LocalDate, List<SleepingSession>> sessionsByNight =
                sleepingSessions.stream()
                        .filter(this::isNightSleep)
                        .collect(Collectors.groupingBy(this::getNightDate));

        Map<Chronotype, Long> chronotypeCounts =
                sessionsByNight.values().stream()
                        .map(this::determineNightChronotype)
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        ));

        if (chronotypeCounts.isEmpty()) {
            return new SleepAnalysisResult<>(
                    Chronotype.PIGEON,
                    "Хронотип пользователя"
            );
        }

        long maxCount = chronotypeCounts.values().stream()
                .mapToLong(Long::longValue)
                .max()
                .orElse(0);

        List<Chronotype> mostFrequent = chronotypeCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .toList();

        Chronotype result = mostFrequent.size() == 1
                ? mostFrequent.get(0)
                : Chronotype.PIGEON;

        return new SleepAnalysisResult<>(
                result,
                "Хронотип пользователя"
        );
    }

    private boolean isNightSleep(SleepingSession session) {
        LocalDateTime start = session.getStart();
        LocalDateTime finish = session.getFinish();

        LocalDateTime nightStart =
                getNightDate(session).atTime(NIGHT_START);

        LocalDateTime nightFinish =
                nightStart.plusDays(1).with(NIGHT_FINISH);

        return start.isBefore(nightFinish)
                && finish.isAfter(nightStart);
    }

    private LocalDate getNightDate(SleepingSession session) {
        LocalDateTime start = session.getStart();

        if (start.toLocalTime().isBefore(NIGHT_FINISH)) {
            return start.toLocalDate().minusDays(1);
        }

        return start.toLocalDate();
    }

    private Chronotype determineNightChronotype(
            List<SleepingSession> sessions
    ) {
        LocalDateTime start = sessions.stream()
                .map(SleepingSession::getStart)
                .min(Comparator.naturalOrder())
                .orElseThrow();

        LocalDateTime finish = sessions.stream()
                .map(SleepingSession::getFinish)
                .max(Comparator.naturalOrder())
                .orElseThrow();

        LocalDate nightDate = getNightDate(sessions.get(0));

        LocalDateTime owlStart = nightDate.atTime(23, 0);
        LocalDateTime owlFinish =
                nightDate.plusDays(1).atTime(9, 0);

        LocalDateTime larkStart = nightDate.atTime(22, 0);
        LocalDateTime larkFinish =
                nightDate.plusDays(1).atTime(7, 0);

        if (start.isAfter(owlStart)
                && finish.isAfter(owlFinish)) {
            return Chronotype.OWL;
        }

        if (start.isBefore(larkStart)
                && finish.isBefore(larkFinish)) {
            return Chronotype.LARK;
        }

        return Chronotype.PIGEON;
    }
}
