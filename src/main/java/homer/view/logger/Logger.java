package homer.view.logger;

import java.io.OutputStream;

import homer.view.View;

/**
 * Simple logger that logs to the selected output stream.
 */
public interface Logger extends View {

    /**
     * Closes the old output stream and starts writing on the new one.
     * @param outputStream the new output stream to log to.
     */
    void setOutputStream(OutputStream outputStream);
}
