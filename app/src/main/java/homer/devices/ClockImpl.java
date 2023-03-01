package homer.devices;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

import homer.api.Clock;

/**
 * Simple {@link Clock} implementation.
 * The clock's time is static and has to be manually set/updated.
 * At initialization the time is set to the current time.
 */
public final class ClockImpl implements Clock {

    private LocalDateTime dateTime = LocalDateTime.now();

    @Override
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public void setDateTime(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public void updateTick(final TemporalAmount deltaTime) {
        this.dateTime = this.dateTime.plus(deltaTime);
    }

}
