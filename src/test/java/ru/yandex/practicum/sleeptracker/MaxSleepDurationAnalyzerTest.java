package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MaxSleepDurationAnalyzerTest {

    private MaxSleepDurationAnalyzer maxSleepDurationAnalyzer;
    private List<SleepingSession> sleepingSessions;

    @BeforeEach
    public void setUp() {
        maxSleepDurationAnalyzer = new MaxSleepDurationAnalyzer();
        sleepingSessions = new ArrayList<>();
    }

    @Test
    public void shouldFindMax() {
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
        assertEquals(480, maxSleepDurationAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldFindMaxWithManyArguments() {
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
                LocalDateTime.of(2025, 10, 4, 8, 0),
                SleepQuality.NORMAL
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        sleepingSessions.add(thirdSession);
        assertEquals(540, maxSleepDurationAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldFindMaxWithEqualDuration() {
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
        SleepingSession thirdSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 0),
                LocalDateTime.of(2025, 10, 4, 5, 0),
                SleepQuality.BAD
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        sleepingSessions.add(thirdSession);
        assertEquals(480, maxSleepDurationAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldThrowExceptionWithEmptyList() {
        assertThrows(IllegalArgumentException.class,
                () -> maxSleepDurationAnalyzer.apply(sleepingSessions));
    }
}
