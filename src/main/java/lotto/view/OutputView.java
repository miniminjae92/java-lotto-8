package lotto.view;

import java.util.List;
import lotto.domain.dto.LottoDto;
import lotto.domain.dto.StatisticsDto;

public class OutputView {

    private static final String LS = System.lineSeparator();
    private static final String PURCHASE_AMOUNT_PROMPT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_PROMPT_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_PROMPT_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String PURCHASE_RESULT_MESSAGE = "%d개를 구매했습니다.";
    private static final String STATISTICS_HEADER_MESSAGE = "당첨 통계";
    private static final String DIVIDER_MESSAGE = "---";
    private static final String TOTAL_PROFIT_MESSAGE = "총 수익률은 %.1f%%입니다.";

    public void purchaseAmountPrompt() {
        System.out.println(PURCHASE_AMOUNT_PROMPT_MESSAGE);
    }

    public void winningLottoPrompt() {
        System.out.println(LS + WINNING_NUMBERS_PROMPT_MESSAGE);
    }

    public void bonusNumberPrompt() {
        System.out.println(LS + BONUS_NUMBER_PROMPT_MESSAGE);
    }

    public void printQuantityOfTickets(int count) {
        System.out.println(LS + String.format(PURCHASE_RESULT_MESSAGE, count));
    }

    public void printTickets(List<LottoDto> lottoDtos) {
        for (LottoDto dto : lottoDtos) {
            System.out.println(dto.numbers());
        }
    }

    public void printStatistics(List<StatisticsDto> statisticsDtos) {
        System.out.println();
        System.out.println(STATISTICS_HEADER_MESSAGE);
        System.out.println(DIVIDER_MESSAGE);
        statisticsDtos.forEach(System.out::println);
    }

    public void printTotalReturn(double profitRate) {
        System.out.println(String.format(TOTAL_PROFIT_MESSAGE, profitRate));
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
