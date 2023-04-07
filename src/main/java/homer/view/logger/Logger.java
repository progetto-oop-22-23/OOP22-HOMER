package homer.view.logger;

import java.io.OutputStream;

import homer.view.DeviceViewer;

/**
 * Simple logger that logs to the selected output stream.
 */
public interface Logger extends DeviceViewer {

    /**
     * Closes the old output stream and starts writing on the new one.
     * @param outputStream the new output stream to log to.
     */
    void setOutputStream(OutputStream outputStream);

    /**
     * 
     * @param string string to be logged.
     */
    void log(String string);
}
