package homer.model.lock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

final class SimpleLockTest {

    private final Lock lock = new SimpleLock();

    @BeforeEach
    void testNotNull() {
        assertNotNull(this.lock);
    }

    @RepeatedTest(3)
    void testToggle() {
        final var wasLocked = this.lock.getState().isLocked();
        this.lock.toggle();
        final var isLocked = this.lock.getState().isLocked();
        assertTrue(wasLocked ? !isLocked : isLocked);
    }

}
