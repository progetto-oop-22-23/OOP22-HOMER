package homer.controller;

import homer.view.DeviceViewer;

/**
 * Manages all the views. Can be considered itself a view, since it supports all
 * view-related operations (since it will effectively dispatch them to the
 * actual views).
 */
public interface ViewManager extends DeviceViewer {

    /**
     * 
     * @param view View to be added.
     */
    void addView(DeviceViewer view);

}
