package homer.model.scheduler;

/**
 * A command to be executed by a {@link TimeScheduler}.
 */
public interface SchedulerCommand {

    /**
     * Executes the command.
     */
    void execute();

}
