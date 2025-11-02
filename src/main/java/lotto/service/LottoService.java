package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import lotto.constants.ErrorMessage;
import lotto.domain.LottoIssuer;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;

public class LottoService {

    public Lottos purchaseTickets(int amount) {
        int quantity = validateAmount(amount);
        Supplier<List<Integer>> randomNumbers = () -> Randoms.pickUniqueNumbersInRange(1, 45, 6);
        LottoIssuer issuer = new LottoIssuer(randomNumbers);
        return issuer.issue(quantity);
    }

    public Map<Rank, Integer> getStatistics(Lottos lottos, WinningLotto winningLotto) {
        return lottos.calculateStatistics(winningLotto);
    }

    public double getProfitRate(Map<Rank, Integer> statistics, int issuedCount) {
        double totalPurchaseAmount = issuedCount * 1000;
        double totalPrize = 0;
        for (Entry<Rank, Integer> entry : statistics.entrySet()) {
            if (entry.getValue() != 0) {
                totalPrize += entry.getKey().getPrize() * entry.getValue();
            }
        }
        return totalPrize / totalPurchaseAmount * 100;
    }

    private int validateAmount(int amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_AMOUNT_RANGE_MESSAGE.getMessage());
        }
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_AMOUNT_UNIT_MESSAGE.getMessage());
        }
        return amount / 1000;
    }
}
