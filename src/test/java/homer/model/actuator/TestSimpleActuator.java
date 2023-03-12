package homer.model.actuator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;

import org.junit.jupiter.api.Test;

final class TestSimpleActuator {

    private static final int MIN_POSITION = 0;
    private static final int MAX_POSITION = 100;
    private final Actuator actuator = new SimpleActuator(MIN_POSITION, MAX_POSITION);

    @Test
    void testGetPosition() {
        assertNotNull(this.actuator.getPosition());
    }

    @Test
    void testCommand() {
        final var newPosition = 50;
        commandAndUpdate(newPosition);
        assertEquals(newPosition, this.actuator.getPosition());
    }

    @Test
    void testMinPosition() {
        commandAndUpdate(MIN_POSITION - 100);
        assertEquals(MIN_POSITION, this.actuator.getPosition());
    }

    @Test
    void testMaxPosition() {
        commandAndUpdate(MAX_POSITION + 100);
        assertEquals(MAX_POSITION, this.actuator.getPosition());
    }

    private void commandAndUpdate(final int commandedPosition) {
        this.actuator.command(commandedPosition);
        this.actuator.updateTick(Duration.ofMillis(1));
    }

}
