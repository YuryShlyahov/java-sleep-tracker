package ru.yandex.practicum.sleeptracker.analyzersTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.analyzers.SleeplessNightsAnalyzer;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

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
    public void shouldCountZeroSleeplessNightsWithNormalSleep() {
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
                LocalDateTime.of(2025, 10, 4, 23, 0),
                LocalDateTime.of(2025, 10, 5, 4, 0),
                SleepQuality.BAD
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        sleepingSessions.add(thirdSession);
        sleepingSessions.add(fourthSession);
        assertEquals(0, sleeplessNightsAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldCountZeroInEmptyList() {
        assertEquals(0, sleeplessNightsAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldCountZeroWithOneDay() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 14, 0),
                LocalDateTime.of(2025, 10, 1, 15, 0),
                SleepQuality.GOOD
        );
        sleepingSessions.add(firstSession);
        assertEquals(0, sleeplessNightsAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldCountZeroWithOneEveningSleep() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 0, 0),
                SleepQuality.GOOD
        );
        sleepingSessions.add(firstSession);
        assertEquals(0, sleeplessNightsAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldCountZeroWithOneNightSleep() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 2, 0),
                SleepQuality.GOOD
        );
        sleepingSessions.add(firstSession);
        assertEquals(0, sleeplessNightsAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldCountOneWithOneSleeplessNight() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 0),
                LocalDateTime.of(2025, 10, 4, 7, 0),
                SleepQuality.GOOD
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        assertEquals(1, sleeplessNightsAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldCountOneWithDifferentMonth() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 11, 29, 23, 0),
                LocalDateTime.of(2025, 11, 30, 7, 0),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 12, 1, 23, 0),
                LocalDateTime.of(2025, 12, 2, 7, 0),
                SleepQuality.GOOD
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        assertEquals(1, sleeplessNightsAnalyzer.apply(sleepingSessions).getValue());
    }


    @Test
    public void shouldCountZeroWithWakeAtSix() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 11, 1, 23, 0),
                LocalDateTime.of(2025, 11, 2, 6, 0),
                SleepQuality.GOOD
        );
        sleepingSessions.add(firstSession);
        assertEquals(0, sleeplessNightsAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldCountOneWithChangingMonth() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 11, 30, 23, 0),
                LocalDateTime.of(2025, 12, 1, 6, 0),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 12, 2, 23, 0),
                LocalDateTime.of(2025, 12, 3, 6, 0),
                SleepQuality.GOOD
        );
        SleepingSession thirdSession = new SleepingSession(
                LocalDateTime.of(2025, 12, 3, 23, 0),
                LocalDateTime.of(2025, 12, 4, 6, 0),
                SleepQuality.GOOD
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        sleepingSessions.add(thirdSession);
        assertEquals(1, sleeplessNightsAnalyzer.apply(sleepingSessions).getValue());
    }

}
