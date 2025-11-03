package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.domain.dto.LottoDto;
import lotto.domain.dto.StatisticsDto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        Lottos lottos = purchaseLottos();
        Lotto mainNumbers = askWinningLotto();
        WinningLotto winningLotto = askBonusNumber(mainNumbers);
        showResults(lottos, winningLotto);
    }

    private Lottos purchaseLottos() {
        return retryUntilSuccess(() -> {
            outputView.purchaseAmountPrompt();
            int amount = inputView.readPurchaseAmount();
            Lottos lottos = lottoService.purchaseTickets(amount);
            List<LottoDto> lottoDtos = lottos.toDtoList();
            outputView.printQuantityOfTickets(lottos.getSize());
            outputView.printTickets(lottoDtos);
            return lottos;
        });
    }

    private Lotto askWinningLotto() {
        return retryUntilSuccess(() -> {
            outputView.winningLottoPrompt();
            List<Integer> mainNumbers = inputView.readMainNumbers();
            return new Lotto(mainNumbers);
        });
    }

    private WinningLotto askBonusNumber(Lotto mainNumbers) {
        return retryUntilSuccess(() -> {
            outputView.bonusNumberPrompt();
            int bonusNumber = inputView.readBonusNumber();
            return new WinningLotto(mainNumbers, bonusNumber);
        });
    }

    private void showResults(Lottos lottos, WinningLotto winningLotto) {
        Map<Rank, Integer> statistics = lottoService.getStatistics(lottos, winningLotto);
        double profitRate = lottoService.getProfitRate(statistics, lottos.getSize());
        List<StatisticsDto> statisticsDtos = Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.MISS)
                .map(rank -> new StatisticsDto(rank.getMessage(), statistics.get(rank)))
                .toList();
        outputView.printStatistics(statisticsDtos);
        outputView.printTotalReturn(profitRate);
    }

    private <T> T retryUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

}
