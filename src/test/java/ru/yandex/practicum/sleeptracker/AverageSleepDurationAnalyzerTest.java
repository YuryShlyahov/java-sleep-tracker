package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AverageSleepDurationAnalyzerTest {
    private AverageSleepDurationAnalyzer averageSleepDurationAnalyzer;
    private List<SleepingSession> sleepingSessions;

    @BeforeEach
    public void setUp() {
        averageSleepDurationAnalyzer = new AverageSleepDurationAnalyzer();
        sleepingSessions = new ArrayList<>();
    }

    @Test
    public void shouldCountAverageDurationOfThreeSessions() {
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
        assertEquals(420, averageSleepDurationAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldCountAverageWithFractionalValue() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 5, 0),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 5, 1),
                SleepQuality.BAD
        );
        SleepingSession thirdSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 0),
                LocalDateTime.of(2025, 10, 4, 5, 3),
                SleepQuality.NORMAL
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        sleepingSessions.add(thirdSession);
        assertEquals(361.3333,
                averageSleepDurationAnalyzer.apply(sleepingSessions).getValue(), 0.0001);
    }

    @Test
    public void shouldCountAverageValueWithOneSession() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 5, 0),
                SleepQuality.GOOD
        );
        sleepingSessions.add(firstSession);
        assertEquals(360, averageSleepDurationAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldThrowExceptionWithEmptyList() {
        assertThrows(IllegalArgumentException.class,
                () -> averageSleepDurationAnalyzer.apply(sleepingSessions));
    }
}
