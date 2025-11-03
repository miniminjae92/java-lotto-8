package lotto.common;

public enum ErrorMessage {
    INVALID_NUMBER_COUNT("로또 번호는 6개여야 합니다."),
    DUPLICATE_LOTTO_NUMBER("로또 번호가 중복되었습니다."),
    NUMBER_OUT_OF_RANGE("로또 번호가 유효하지 않은 범위에 속합니다."),
    DUPLICATE_BONUS_NUMBER("보너스 번호가 중복되었습니다."),
    EMPTY_INPUT("입력이 비어있습니다, 다시 입력해주세요."),
    INVALID_LOTTO_FORMAT(" 쉼표를 사용하여 숫자 6개를 입력해주세요."),
    INVALID_BONUS_FORMAT("보너스 번호는 하나만 입력해주세요."),
    NOT_A_NUMBER("올바른 숫자를 입력해주세요."),
    INVALID_PURCHASE_AMOUNT_UNIT("구입금액은 1,000원 단위여야 합니다."),
    INVALID_PURCHASE_AMOUNT_RANGE("구입금액은 1,000원 이상이어야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
