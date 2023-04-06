package homer.model.airquality;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

class AirQualityStateTest {

    @Test
    void requireGreaterOrEqualThanZeroConstructorTest() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new AirQualityStateImpl(-1, 0, 0, 0));
        assertThrowsExactly(IllegalArgumentException.class, () -> new AirQualityStateImpl(0, -1, 0, 0));
        assertThrowsExactly(IllegalArgumentException.class, () -> new AirQualityStateImpl(0, 0, -1, 0));
        assertThrowsExactly(IllegalArgumentException.class, () -> new AirQualityStateImpl(0, 0, 0, -1));
        assertDoesNotThrow(() -> new AirQualityStateImpl(0, 0, 0, 0));
    }

    @Test
    void requireGreaterOrEqualThanZeroSettersTest() {
        final AirQualityStateImpl airQuality = new AirQualityStateImpl(0, 0, 0, 0);
        assertThrowsExactly(IllegalArgumentException.class, () -> airQuality.setCO2(-1));
        assertThrowsExactly(IllegalArgumentException.class, () -> airQuality.setPM10(-1));
        assertThrowsExactly(IllegalArgumentException.class, () -> airQuality.setToxicGasPercentage(-1));
        assertThrowsExactly(IllegalArgumentException.class, () -> airQuality.setPM25(-1));
        assertDoesNotThrow(() -> airQuality.setCO2(1));
        assertDoesNotThrow(() -> airQuality.setToxicGasPercentage(1));
        assertDoesNotThrow(() -> airQuality.setPM10(1));
        assertDoesNotThrow(() -> airQuality.setPM25(1));
    }

    @Test
    void requireToxicGasPercentageSmallerThanZero() {
        final double greaterThanOneHundredDiscrete = 101;
        final double greaterThanOneHundredContinuous = 100.5;
        final var airQualityState = new AirQualityStateImpl(0, 0, 0, 0);
        assertThrowsExactly(IllegalArgumentException.class,
                () -> airQualityState.setToxicGasPercentage(greaterThanOneHundredDiscrete));
        assertThrowsExactly(IllegalArgumentException.class,
                () -> airQualityState.setToxicGasPercentage(greaterThanOneHundredContinuous));
    }
}
