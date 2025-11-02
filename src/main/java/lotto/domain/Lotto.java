package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.common.ErrorMessage;
import static lotto.common.LottoConstants.*;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = sorted(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplication(numbers);
    }

    public int countMatch(Lotto other) {
        return (int) other.numbers.stream()
                .filter(this.numbers::contains)
                .count();
    }

    public boolean contains(int number) {
        return this.numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_COUNT.getMessage());
        }
    }

    private static void validateRange(List<Integer> numbers) {
        for (int i : numbers) {
            if (i < LOTTO_NUMBER_MIN || i > LOTTO_NUMBER_MAX) {
                throw new IllegalArgumentException(ErrorMessage.NUMBER_OUT_OF_RANGE.getMessage());
            }
        }
    }

    private static void validateDuplication(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    private static List<Integer> sorted(List<Integer> numbers) {
        return numbers.stream().sorted(Integer::compareTo).toList();
    }
}
