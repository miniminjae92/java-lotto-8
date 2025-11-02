package lotto.constants;

public enum ErrorMessage {
    INVALID_NUMBER_COUNT("로또 번호는 6개여야 합니다."),
    DUPLICATE_LOTTO_NUMBER("로또 번호가 중복되었습니다."),
    NUMBER_OUT_OF_RANGE("로또 번호가 유효하지 않은 범위에 속합니다."),
    DUPLICATE_BONUS_NUMBER("보너스 번호가 중복되었습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
