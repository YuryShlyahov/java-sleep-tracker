package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.analyzers.*;

import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {
    private static final List<Function<List<SleepingSession>, ? extends SleepAnalysisResult<?>>> functions =
            List.of(new AverageSleepDurationAnalyzer(),
                    new BadSleepCountAnalyzer(),
                    new ChronotypeAnalyzer(),
                    new MaxSleepDurationAnalyzer(),
                    new MinSleepDurationAnalyzer(),
                    new SleeplessNightsAnalyzer(),
                    new SessionCountAnalyzer());

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: не указан путь к файлу с данными о сне");
            return;
        }
        SleepingSessionLoader sleepingSessionLoader =
                new SleepingSessionLoader(args[0]);
        List<SleepingSession> sleepingSessionList = sleepingSessionLoader.load();
        functions.forEach(function -> {
            SleepAnalysisResult<?> result = function.apply(sleepingSessionList);
            String resultDescription = result.getDescription();
            String value = String.valueOf(result.getValue());
            System.out.println(resultDescription + " : " + value);
        });
    }
}