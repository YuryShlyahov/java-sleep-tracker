package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
    LocalDateTime start;
    LocalDateTime finish;
    SleepQuality sleepQuality;

    public SleepingSession(LocalDateTime start, LocalDateTime finish, String sleepQuality) {
        this.start = start;
        this.finish = finish;
        this.sleepQuality = SleepQuality.valueOf(sleepQuality);
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "start=" + start +
                ", finish=" + finish +
                ", sleepQuality='" + sleepQuality + '\'' +
                '}';
    }
}
