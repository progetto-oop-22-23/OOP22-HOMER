package homer.model.scheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import homer.common.bounds.Bounds;
import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import homer.model.scheduler.TimeScheduler.ParameterResult;

final class TemperatureSchedulerTest {

    private static final Bounds<LocalTime> TIME_RANGE = new Bounds<>(LocalTime.of(9, 0), LocalTime.of(12, 0));
    private static final Bounds<Temperature> TEMP_RANGE = new Bounds<>(TemperatureFactory.fromCelsius(19),
            TemperatureFactory.fromCelsius(22));
    private static final LocalTime TIME_EARLY = LocalTime.of(0, 0);
    private static final LocalTime TIME_IN_RANGE = LocalTime.of(10, 0);
    private static final LocalTime TIME_LATER = LocalTime.of(23, 0);
    private static final Temperature TEMP_BELOW = TemperatureFactory.fromCelsius(0);
    private static final Temperature TEMP_IN_RANGE = TemperatureFactory.fromCelsius(20);
    private static final Temperature TEMP_ABOVE = TemperatureFactory.fromCelsius(30);

    private TimeSchedulerImpl<Temperature> scheduler;

    @BeforeEach
    void start() {
        this.scheduler = new TimeSchedulerImpl<>();
    }

    @Test
    void testAddSchedule() {
        initSchedules();

        final var schedule = scheduler.getSchedules().values().stream().findAny();
        assertEquals(TIME_RANGE, schedule.get().timeBounds());
        assertEquals(TEMP_RANGE, schedule.get().paramBounds());
    }

    @Test
    void testOverlapping() {
        initSchedules();

        checkOverlapping(TIME_RANGE);
        checkOverlapping(new Bounds<>(TIME_EARLY, TIME_IN_RANGE));
        checkOverlapping(new Bounds<>(TIME_IN_RANGE, TIME_LATER));
        checkOverlapping(new Bounds<>(TIME_IN_RANGE, TIME_IN_RANGE));
    }

    @Test
    void testRemoveSchedule() {
        initSchedules();

        final Optional<ScheduleId> id = scheduler.getSchedules().keySet().stream().findFirst();
        assertTrue(id.isPresent());
        scheduler.removeSchedule(id.get());
        assertEquals(0, scheduler.getSchedules().size());
    }

    @Test
    void testCheckSchedule() {
        initSchedules();

        assertEquals(ParameterResult.NOT_FOUND, scheduler.checkSchedule(TIME_EARLY, TEMP_IN_RANGE));
        assertEquals(ParameterResult.NOT_FOUND, scheduler.checkSchedule(TIME_LATER, TEMP_IN_RANGE));

        assertEquals(ParameterResult.BELOW_BOUNDS, scheduler.checkSchedule(TIME_IN_RANGE, TEMP_BELOW));
        assertEquals(ParameterResult.IN_BOUNDS, scheduler.checkSchedule(TIME_IN_RANGE, TEMP_IN_RANGE));
        assertEquals(ParameterResult.ABOVE_BOUNDS, scheduler.checkSchedule(TIME_IN_RANGE, TEMP_ABOVE));
    }

    private void initSchedules() {
        assertEquals(0, scheduler.getSchedules().size());
        scheduler.addSchedule(TIME_RANGE, TEMP_RANGE);
        assertEquals(1, scheduler.getSchedules().size());
    }

    private void checkOverlapping(final Bounds<LocalTime> newTimeRange) {
        final var sizeBefore = scheduler.getSchedules().size();
        assertThrowsExactly(IllegalArgumentException.class, () -> scheduler.addSchedule(newTimeRange, TEMP_RANGE));
        assertEquals(sizeBefore, scheduler.getSchedules().size());
    }
}
