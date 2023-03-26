package homer.core;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of {@link SimManagerViewObserver}
 */
public class SimManagerImpl implements SimManagerViewObserver {

    private static final Duration DEFAULT_SIM_STEP_PERIOD = Duration.of(1, TimeUnit.MINUTES.toChronoUnit());
    private static final Duration DEFAULT_REAL_STEP_PERIOD = Duration.of(6, TimeUnit.MILLISECONDS.toChronoUnit());
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Duration simStepPeriod = DEFAULT_SIM_STEP_PERIOD;
    private Duration realStepPeriod = DEFAULT_REAL_STEP_PERIOD;
    private ScheduledFuture<?> loopHandle;
    private Runnable loopRunnable;

    /**
     * Creates a new {@link SimManagerImpl} with the given {@code Runnable} and
     * starts running it indefinitely.
     * 
     * @param loopRunnable the simulation code to run at each iteration.
     */
    public SimManagerImpl(final Runnable loopRunnable) {
        this.loopRunnable = loopRunnable;
        resume();
    }

    @Override
    public void resume() {
        this.loopHandle = scheduler.scheduleAtFixedRate(this.loopRunnable, 0, this.realStepPeriod.toMillis(),
                TimeUnit.MILLISECONDS);
    }

    @Override
    public void pause() {
        this.loopHandle.cancel(true);
    }

    @Override
    public Duration getSimStepPeriod() {
        return this.simStepPeriod;
    }

    @Override
    public void setSimStepPeriod(final Duration simStepTime) {
        this.simStepPeriod = simStepTime;
    }

}
