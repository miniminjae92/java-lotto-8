package lotto.view;

import static lotto.common.LottoConstants.LOTTO_NUMBER_COUNT;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.common.ErrorMessage;

public class InputView {

    public int readPurchaseAmount() {
        String input = Console.readLine();
        validateNotBlank(input);
        return parseInt(input);
    }

    public List<Integer> readMainNumbers() {
        String input = Console.readLine();
        validateNotBlank(input);
        return parseNumbers(input);
    }

    public int readBonusNumber() {
        String input = Console.readLine();
        validateNotBlank(input);
        return parseInt(input);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_COUNT.getMessage());
        }
    }

    private static void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT_MESSAGE.getMessage());
        }
    }

    private List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(this::parseInt)
                .collect(Collectors.toList());
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input.strip());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_MESSAGE.getMessage());
        }
    }
}
