package homer.controller;

import homer.view.View;

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
