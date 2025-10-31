package lotto.constants;

public enum ErrorMessage {
    INVALID_NUMBER_COUNT("로또 번호는 6개여야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
