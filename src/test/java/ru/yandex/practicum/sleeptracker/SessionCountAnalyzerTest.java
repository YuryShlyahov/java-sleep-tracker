package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionCountAnalyzerTest {
    private SessionCountAnalyzer sessionCountAnalyzer;
    private List<SleepingSession> sleepingSessions;

    @BeforeEach
    public void setUp() {
        sessionCountAnalyzer = new SessionCountAnalyzer();
        sleepingSessions = new ArrayList<>();
    }

    @Test
    public void shouldCountTwoSessions() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 0),
                LocalDateTime.of(2025, 10, 3, 7, 0),
                SleepQuality.GOOD
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        assertEquals(2, sessionCountAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldCountZeroSessions(){
        assertEquals(0, sessionCountAnalyzer.apply(sleepingSessions).getValue());
    }
}
