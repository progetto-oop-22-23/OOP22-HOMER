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

    /**
     * Updates the time rate in the view.
     * 
     * @param timeRate the sim time rate.
     */
    void setTimeRate(long timeRate);

    /**
     * Displays an error.
     * 
     * @param title the title.
     * @param text  the content text.
     */
    void showError(String title, String text);

}
