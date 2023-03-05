package homer.model.environment;

/**
 * This interface models an object placed in an environment.
 */
public interface EnvironmentObject {

    /**
     * Move the object to a new environment.
     * 
     * @param environment The environment in which the object is to be placed.
     */
    void moveTo(Environment environment);

}
