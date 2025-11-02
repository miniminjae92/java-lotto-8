package lotto.view;

import java.util.List;
import lotto.domain.dto.LottoDto;
import lotto.domain.dto.StatisticsDto;

public class OutputView {

    private static final String LS = System.lineSeparator();

    public void purchaseAmountPrompt() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void winningLottoPrompt() {
        System.out.println(LS + "당첨 번호를 입력해 주세요.");
    }

    public void bonusNumberPrompt() {
        System.out.println(LS + "보너스 번호를 입력해 주세요.");
    }

    public void printQuantityOfTickets(int count) {
        System.out.println(LS + count + "개를 구매했습니다.");
    }

    public void printTickets(List<LottoDto> lottoDtos) {
        for (LottoDto dto : lottoDtos) {
            System.out.println(dto.numbers());
        }
    }

    public void printStatistics(List<StatisticsDto> statisticsDtos) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        statisticsDtos.forEach(System.out::println);
    }

    public void printTotalReturn(double profitRate) {
        System.out.printf("총 수익률은 %.1f입니다.%n", profitRate);
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
