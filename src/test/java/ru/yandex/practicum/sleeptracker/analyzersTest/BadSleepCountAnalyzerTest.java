package ru.yandex.practicum.sleeptracker.analyzersTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.analyzers.BadSleepCountAnalyzer;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadSleepCountAnalyzerTest {
    private BadSleepCountAnalyzer badSleepCountAnalyzer;
    private List<SleepingSession> sleepingSessions;

    @BeforeEach
    public void setUp() {
        badSleepCountAnalyzer = new BadSleepCountAnalyzer();
        sleepingSessions = new ArrayList<>();
    }

    @Test
    public void shouldCountTwoBadSessions() {
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
        assertEquals(2, badSleepCountAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldCountZeroBadSessions() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 5, 0),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 6, 0),
                SleepQuality.GOOD
        );
        SleepingSession thirdSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 0),
                LocalDateTime.of(2025, 10, 4, 7, 0),
                SleepQuality.NORMAL
        );
        SleepingSession fourthSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 5, 23, 0),
                LocalDateTime.of(2025, 10, 6, 4, 0),
                SleepQuality.NORMAL
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        sleepingSessions.add(thirdSession);
        sleepingSessions.add(fourthSession);
        assertEquals(0, badSleepCountAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldCountZeroWithEmptyList() {
        assertEquals(0, badSleepCountAnalyzer.apply(sleepingSessions).getValue());
    }
}
