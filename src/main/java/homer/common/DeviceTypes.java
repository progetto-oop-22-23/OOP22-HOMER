package homer.common;

import java.util.Set;

/**
 * Deals with the types of various devices. 
 */
public interface DeviceTypes {

    /**
     * 
     * @param type The type whose validity is being checked
     * @return validity of the device
     */
    boolean isValid(String type); 

    /**
     * 
     * @param type the type to be added 
     */
    void addType(String type);

    /**
     * 
     * @return All the valid types
     */
    Set<String> getValidTypes();
}
