package lotto.domain;

import java.util.List;
import lotto.constants.ErrorMessage;

public class WinningLotto {

    private final Lotto mainNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> mainNumbers, int bonusNumber) {
        this.mainNumbers = new Lotto(mainNumbers);
        validateNumberRange(bonusNumber);
        validateBonusNotInMain(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public Rank calculateRank(Lotto other) {
        int countMatch = other.countMatch(mainNumbers);
        boolean hasBonus = other.contains(bonusNumber);
        return Rank.valueOf(countMatch, hasBonus);
    }

    private static void validateNumberRange(int numbers) {
        if (numbers < 1 || numbers > 45) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    private void validateBonusNotInMain(int bonusNumber) {
        if (this.mainNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }
}
