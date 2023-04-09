package homer.common.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class ClockImplTest {

    private final Clock clock = new ClockImpl();

    @BeforeEach
    void testNotNull() {
        assertNotNull(this.clock);
    }

    @Test
    void testGetTime() {
        assertNotNull(this.clock.getDateTime());
    }

    @Test
    void testSetTime() {
        final LocalDateTime newDateTime = LocalDateTime.now();
        this.clock.setDateTime(newDateTime);
        assertEquals(this.clock.getDateTime(), newDateTime);
    }

    @Test
    void testUpdateTick() {
        checkTick(Duration.ofSeconds(1));
        checkTick(Duration.ofHours(1));
        checkTick(Duration.ofDays(1));
    }

    private void checkTick(final Duration deltaTime) {
        final LocalDateTime currentDateTime = this.clock.getDateTime();
        this.clock.updateTick(deltaTime);
        assertEquals(this.clock.getDateTime(), currentDateTime.plus(deltaTime));
    }

}
