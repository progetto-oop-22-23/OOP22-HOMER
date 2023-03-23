package homer.controller.command;

import homer.controller.Controller;

/**
 * Models a command that the user can send to the controller.
 * Commands are supposed to only be constructed in the view, and only
 * handled by controllers.
 */
public interface Command {

    /**
     * Executes the command.
     */
    void execute(Controller controller);

}
