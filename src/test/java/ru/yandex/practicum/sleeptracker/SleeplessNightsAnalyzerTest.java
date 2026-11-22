package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsAnalyzerTest {
    private SleeplessNightsAnalyzer sleeplessNightsAnalyzer;
    private List<SleepingSession> sleepingSessions;

    @BeforeEach
    public void setUp() {
        sleeplessNightsAnalyzer = new SleeplessNightsAnalyzer();
        sleepingSessions = new ArrayList<>();
    }

    @Test
    public void shouldCountZeroSleeplessNights() {
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
        SleepingSession fourthSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 5, 23, 0),
                LocalDateTime.of(2025, 10, 6, 4, 0),
                SleepQuality.BAD
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        sleepingSessions.add(thirdSession);
        sleepingSessions.add(fourthSession);
        assertEquals(0, sleeplessNightsAnalyzer.apply(sleepingSessions).getValue());
    }

}
