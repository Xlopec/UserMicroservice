package domain.utils;

public final class Preconditions {

    private Preconditions() {
        // against reflection constructor invocation
        throw new IllegalStateException("reflection tricks))");
    }

    public static void require(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void require(boolean condition, String message, Object...format) {
        if (!condition) {
            throw new IllegalArgumentException(String.format(message, format));
        }
    }
}
