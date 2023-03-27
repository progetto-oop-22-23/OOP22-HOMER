package homer.controller;

import homer.view.View;

/**
 * Manages all the views. Can be considered itself a view, since it supports all
 * view-related operations (since it will effectively dispatch them to the
 * actual views).
 */
public interface ViewManager extends View {

    /**
     * 
     * @param view View to be added.
     */
    void addView(View view);

    /**
     * 
     * @param view View to be removed.
     */
    void removeView(View view);

}
