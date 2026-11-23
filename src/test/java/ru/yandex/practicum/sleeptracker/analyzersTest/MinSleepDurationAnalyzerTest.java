package ru.yandex.practicum.sleeptracker.analyzersTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.analyzers.MinSleepDurationAnalyzer;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MinSleepDurationAnalyzerTest {

    private MinSleepDurationAnalyzer minSleepDurationAnalyzer;
    private List<SleepingSession> sleepingSessions;

    @BeforeEach
    public void setUp() {
        minSleepDurationAnalyzer = new MinSleepDurationAnalyzer();
        sleepingSessions = new ArrayList<>();
    }

    @Test
    public void shouldFindMin() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 5, 0),
                SleepQuality.BAD
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        assertEquals(360, minSleepDurationAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldFindMinWithManyArguments() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 5, 0),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 6, 0),
                SleepQuality.BAD
        );
        SleepingSession thirdSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 0),
                LocalDateTime.of(2025, 10, 4, 7, 0),
                SleepQuality.NORMAL
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        sleepingSessions.add(thirdSession);
        assertEquals(360, minSleepDurationAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldFindMinWithEqualDuration() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 7, 0),
                SleepQuality.BAD
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        assertEquals(480, minSleepDurationAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldThrowExceptionWithEmptyList() {
        assertThrows(IllegalArgumentException.class,
                () -> minSleepDurationAnalyzer.apply(sleepingSessions));
    }
}
