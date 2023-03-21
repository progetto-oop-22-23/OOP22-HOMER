import java.io.OutputStream;

public interface Logger {

    OutputStream getOutputStream();

    OutputStream setOutputStream();

    void logEvent(); // TODO FIX
}
