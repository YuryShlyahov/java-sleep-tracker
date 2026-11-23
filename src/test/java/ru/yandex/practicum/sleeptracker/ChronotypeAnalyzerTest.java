package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class ChronotypeAnalyzerTest {

    private ChronotypeAnalyzer chronotypeAnalyzer;
    private List<SleepingSession> sleepingSessions;

    @BeforeEach
    public void setUp() {
        chronotypeAnalyzer = new ChronotypeAnalyzer();
        sleepingSessions = new ArrayList<>();
    }
}
