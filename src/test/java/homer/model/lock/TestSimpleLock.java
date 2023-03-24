package homer.model.lock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

final class TestSimpleLock {

    private final Lock lock = new SimpleLock();

    @BeforeEach
    void testNotNull() {
        assertNotNull(this.lock);
    }

    @RepeatedTest(value = 3)
    void testToggle() {
        final var wasLocked = this.lock.getState();
        this.lock.toggle();
        final var isLocked = this.lock.getState();
        assertTrue(wasLocked ? !isLocked : isLocked);
    }

}
