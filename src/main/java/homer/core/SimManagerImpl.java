package homer.core;

import java.time.Duration;
import java.util.Optional;
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
    private Optional<ScheduledFuture<?>> loopHandle = Optional.empty();
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
        if (this.loopHandle.isEmpty()) {
            this.loopHandle = Optional.of(scheduler.scheduleAtFixedRate(this.loopRunnable, 0,
                    this.realStepPeriod.toMillis(), TimeUnit.MILLISECONDS));
        }
    }

    @Override
    public void pause() {
        if (this.loopHandle.isPresent()) {
            this.loopHandle.get().cancel(true);
            this.loopHandle = Optional.empty();
        }
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
