package homer.view.sim;

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

}
