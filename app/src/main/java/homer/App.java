package homer;

public final class App {
    private App() {
        throw new UnsupportedOperationException("App should not be instantiated");
    }

    public static void main(final String[] args) {
    }

    public static String getGreeting() {
        return "Hello, World!";
    }
}
