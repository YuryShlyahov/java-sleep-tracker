package ru.yandex.practicum.sleeptracker;

public enum SleepQuality {
    GOOD("хороший сон"),
    NORMAL("нормальный сон"),
    BAD("плохой сон");

    private final String description;

    SleepQuality(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
