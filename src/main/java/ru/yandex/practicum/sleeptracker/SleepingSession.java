package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
    private final LocalDateTime start;
    private final LocalDateTime finish;
    private final SleepQuality sleepQuality;

    public SleepingSession(LocalDateTime start, LocalDateTime finish, SleepQuality sleepQuality) {
        if (!start.isBefore(finish)) {
            throw new IllegalArgumentException("время начала сна идет после конца сна");
        }
        this.start = start;
        this.finish = finish;
        this.sleepQuality = sleepQuality;
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "start=" + start +
                ", finish=" + finish +
                ", sleepQuality=" + sleepQuality +
                '}';
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }
}
