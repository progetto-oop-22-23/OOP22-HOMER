package homer.controller.command;

import java.util.Optional;

/**
 * Models a command that the user can send to the controller.
 */
public interface Command {
    /**
     * 
     * @return the new state that should be set after the command is executed
     */
    Optional<Object> getNewState();
}
