package homer;

/**
 * Main application class.
 */
public final class App {
    private App() {
        throw new UnsupportedOperationException("App should not be instantiated");
    }

    /**
     * Application entry point.
     * 
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
    }

    // CHECKSTYLE: MissingJavadocMethod OFF
    // Rule disabled because it's a temporary method used for CI.
    public static String getGreeting() {
        return "Hello, World!";
    }
    // CHECKSTYLE: MissingJavadocMethod ON
}
