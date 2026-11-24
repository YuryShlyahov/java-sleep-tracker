package ru.yandex.practicum.sleeptracker.analyzersTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.analyzers.ChronotypeAnalyzer;
import ru.yandex.practicum.sleeptracker.enums.Chronotype;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChronotypeAnalyzerTest {

    private ChronotypeAnalyzer chronotypeAnalyzer;
    private List<SleepingSession> sleepingSessions;

    @BeforeEach
    public void setUp() {
        chronotypeAnalyzer = new ChronotypeAnalyzer();
        sleepingSessions = new ArrayList<>();
    }

    @Test
    public void shouldReturnPigeonWithEmptyList() {
        assertEquals(Chronotype.PIGEON, chronotypeAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldReturnOwlWithManyOwls() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 2, 6, 0),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 1),
                LocalDateTime.of(2025, 10, 3, 9, 1),
                SleepQuality.BAD
        );
        SleepingSession thirdSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 1),
                LocalDateTime.of(2025, 10, 4, 9, 1),
                SleepQuality.NORMAL
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        sleepingSessions.add(thirdSession);
        assertEquals(Chronotype.OWL, chronotypeAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldReturnLarkWithManyLarks() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 21, 59),
                LocalDateTime.of(2025, 10, 2, 6, 59),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 21, 59),
                LocalDateTime.of(2025, 10, 3, 6, 59),
                SleepQuality.BAD
        );
        SleepingSession thirdSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 1),
                LocalDateTime.of(2025, 10, 4, 9, 1),
                SleepQuality.NORMAL
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        sleepingSessions.add(thirdSession);
        assertEquals(Chronotype.LARK, chronotypeAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldReturnPigeonWithManyPigeons() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 2, 6, 0),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 22, 0),
                LocalDateTime.of(2025, 10, 3, 6, 0),
                SleepQuality.BAD
        );
        SleepingSession thirdSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 1),
                LocalDateTime.of(2025, 10, 4, 9, 1),
                SleepQuality.NORMAL
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        sleepingSessions.add(thirdSession);
        assertEquals(Chronotype.PIGEON, chronotypeAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldReturnPigeonWithEquality() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 21, 59),
                LocalDateTime.of(2025, 10, 2, 6, 59),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 22, 0),
                LocalDateTime.of(2025, 10, 3, 6, 0),
                SleepQuality.BAD
        );
        SleepingSession thirdSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 1),
                LocalDateTime.of(2025, 10, 4, 9, 1),
                SleepQuality.NORMAL
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        sleepingSessions.add(thirdSession);
        assertEquals(Chronotype.PIGEON, chronotypeAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldReturnPigeonWithEqualityOfOwlAndLark() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 21, 59),
                LocalDateTime.of(2025, 10, 2, 6, 59),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 1),
                LocalDateTime.of(2025, 10, 3, 9, 2),
                SleepQuality.BAD
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        assertEquals(Chronotype.PIGEON, chronotypeAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldReturnOwlWithManySleepsAtOneNight() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 1),
                LocalDateTime.of(2025, 10, 2, 3, 59),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 4, 40),
                LocalDateTime.of(2025, 10, 2, 9, 2),
                SleepQuality.BAD
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        assertEquals(Chronotype.OWL, chronotypeAnalyzer.apply(sleepingSessions).getValue());
    }

    @Test
    public void shouldIgnoreSleepAtDay() {
        SleepingSession firstSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 21, 59),
                LocalDateTime.of(2025, 10, 2, 5, 59),
                SleepQuality.GOOD
        );
        SleepingSession secondSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 10, 40),
                LocalDateTime.of(2025, 10, 2, 16, 2),
                SleepQuality.BAD
        );
        sleepingSessions.add(firstSession);
        sleepingSessions.add(secondSession);
        assertEquals(Chronotype.LARK, chronotypeAnalyzer.apply(sleepingSessions).getValue());
    }
}
