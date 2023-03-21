package homer.controller.command;


/**
 * Models a command that the user can send to the controller.
 * Every instance implementing this needs to be casted to its proper type inside the receiver.
 * Another way to implement commands would be to require every constructor to take in the controller,
 * and require an execute method in every class implementing command. The thing is, we would only be limited
 * to public methods, and could possibly make the API very large.
 */
public interface Command {

}
