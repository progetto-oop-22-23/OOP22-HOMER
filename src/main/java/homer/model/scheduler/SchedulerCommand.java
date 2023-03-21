package homer.model.scheduler;

/**
 * A command to be executed by the {@link Scheduler}.
 */
public interface SchedulerCommand {

    /**
     * Executes the command.
     */
    void execute();

}
