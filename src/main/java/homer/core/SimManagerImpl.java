package homer.core;

import java.time.Duration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import homer.controller.Controller;
import homer.view.sim.SimManagerView;

/**
 * Implementation of {@link SimManagerViewObserver}.
 */
public final class SimManagerImpl implements SimManager, SimManagerViewObserver {

    private static final String ERROR_TITLE = "Unrecoverable simulation error";
    private static final Duration DEFAULT_SIM_STEP_PERIOD = Duration.of(10, TimeUnit.MILLISECONDS.toChronoUnit());
    private static final Duration DEFAULT_REAL_STEP_PERIOD = Duration.of(10, TimeUnit.MILLISECONDS.toChronoUnit());
    private static final long MIN_TIME_RATE = 1;
    private static final Duration REAL_STEP_PERIOD = DEFAULT_REAL_STEP_PERIOD;
    private static final Duration SIM_STEP_PERIOD = DEFAULT_SIM_STEP_PERIOD;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final SimManagerView view;
    private final Runnable loopRunnable;
    private final Set<DiscreteObject> observers = new HashSet<>();
    private Optional<ScheduledFuture<?>> loopHandle = Optional.empty();
    private long timeRate = MIN_TIME_RATE;

    /**
     * Creates a new {@link SimManagerImpl} with the given {@code Controller} and
     * starts running it indefinitely.
     * 
     * @param view       the simulation manager view.
     * @param controller the controller.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "This is a design choice to be able to set the view"
            + " and interface with the controller")
    public SimManagerImpl(final SimManagerView view, final Controller controller) {
        Objects.requireNonNull(controller);
        this.view = Objects.requireNonNull(view);
        updateView();
        this.loopRunnable = () -> {
            try {
                final var dt = getSimStepPeriod().multipliedBy(timeRate);
                controller.updateTick(dt);
                controller.getDeviceManager().getDevices().values().stream()
                        .filter(DiscreteObject.class::isInstance)
                        .forEach(d -> ((DiscreteObject) d).updateTick(dt));
                view.setDateTime(controller.getClock().getDateTime());
                this.observers.forEach(o -> o.updateTick(dt));
            } catch (final Exception e) { // NOPMD
                // CHECKSTYLE: LineLength OFF
                // Link in comment is breaking the rule.
                /**
                 * Suppressed because it's necessary to catch every exception.
                 * Any exception being thrown here will cause the ScheduledExecutorService to
                 * stop running the task without any error or message. Therefore we want to
                 * catch anything to be able to alert the user, who would otherwise only see the
                 * clock stopping and various actions not working.
                 * It was chosen to do it here for simplicity, because catching the exception of
                 * the ScheduledFuture would require another thread, leading to other possible
                 * side-effects.
                 * More on it here:
                 * https://www.dontpanicblog.co.uk/2021/05/15/exception-handling-in-scheduledexecutorservice/
                 * https://stackoverflow.com/questions/6894595/scheduledexecutorservice-exception-handling
                 * http://web.archive.org/web/20190307143204/http://
                 * code.nomad-labs.com:80/2011/12/09/mother-fk-the-scheduledexecutorservice
                 * Link above is split because CHECKSTYLE suppression is not working apparently.
                 */
                // CHECKSTYLE: LineLength ON
                final var msg = new StringBuilder(e.toString() + "\n"
                        + e.getMessage() + "\n");
                for (final var line : e.getStackTrace()) {
                    msg.append(line + "\n");
                }
                view.showError(ERROR_TITLE, msg.toString());
                /**
                 * If an exception occurs in the loop, the ScheduledExecutorService will not
                 * keep running it. Since we are catching the exceptions to show an alert, we
                 * still want the loop to stop, otherwise the exceptions could be repeated in a
                 * loop.
                 */
                throw e;
            }
        };
        resume();
    }

    @Override
    public void resume() {
        if (this.loopHandle.isEmpty()) {
            this.loopHandle = Optional.of(scheduler.scheduleAtFixedRate(this.loopRunnable, 0,
                    REAL_STEP_PERIOD.toNanos(), TimeUnit.NANOSECONDS));
        }
    }

    @Override
    public void pause() {
        cancelLoop(false);
    }

    @Override
    public void shutdown() {
        cancelLoop(true);
        this.scheduler.shutdown();
    }

    @Override
    public void setTimeRate(final long timeRate) {
        this.timeRate = Math.max(MIN_TIME_RATE, timeRate);
        updateView();
    }

    @Override
    public void addObserver(final DiscreteObject observer) {
        this.observers.add(Objects.requireNonNull(observer));
    }

    private Duration getSimStepPeriod() {
        return SIM_STEP_PERIOD;
    }

    private void updateView() {
        this.view.setTimeRate(this.timeRate);
    }

    private void cancelLoop(final boolean mayInterruptIfRunning) {
        if (this.loopHandle.isPresent()) {
            this.loopHandle.get().cancel(mayInterruptIfRunning);
            this.loopHandle = Optional.empty();
        }
    }
}
