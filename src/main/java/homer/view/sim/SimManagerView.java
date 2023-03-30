package homer.view.sim;

import java.time.LocalDateTime;

import homer.core.SimManagerViewObserver;

/**
 * View for the control of the simulation.
 */
public interface SimManagerView {

    /**
     * Sets the simulation manager controller.
     * 
     * @param simManager the simulation manager controller.
     */
    void setObserver(SimManagerViewObserver simManager);

    /**
     * Updates the datetime in the view.
     * 
     * @param simTime the sim datetime.
     */
    void setDateTime(LocalDateTime simTime);

}
