package homer.model.lock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

final class TestSimpleLock {

    private final SimpleLock lock = new SimpleLock();

    @BeforeEach
    void testNotNull() {
        assertNotNull(this.lock);
    }

    @Test
    void testGetType() {
        assertEquals(SimpleLock.DEVICE_TYPE, lock.getInfo().getType());
    }

    @RepeatedTest(value = 3)
    void testToggle() {
        final var wasLocked = this.lock.getState();
        this.lock.toggle();
        final var isLocked = this.lock.getState();
        assertTrue(wasLocked ? !isLocked : isLocked);
    }

}
