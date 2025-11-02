package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import lotto.common.ErrorMessage;
import lotto.domain.LottoIssuer;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;

import static lotto.common.LottoConstants.*;

public class LottoService {

    public Lottos purchaseTickets(int amount) {
        int quantity = validateAmount(amount);
        Supplier<List<Integer>> randomNumbers = () -> Randoms.pickUniqueNumbersInRange(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_NUMBER_COUNT);
        LottoIssuer issuer = new LottoIssuer(randomNumbers);
        return issuer.issue(quantity);
    }

    public Map<Rank, Integer> getStatistics(Lottos lottos, WinningLotto winningLotto) {
        return lottos.calculateStatistics(winningLotto);
    }

    public double getProfitRate(Map<Rank, Integer> statistics, int issuedCount) {
        double totalPurchaseAmount = issuedCount * LOTTO_PRICE;
        double totalPrize = 0;
        for (Entry<Rank, Integer> entry : statistics.entrySet()) {
            if (entry.getValue() != 0) {
                totalPrize += entry.getKey().getPrize() * entry.getValue();
            }
        }
        return totalPrize / totalPurchaseAmount * PERCENT;
    }

    private int validateAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_AMOUNT_RANGE_MESSAGE.getMessage());
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_AMOUNT_UNIT_MESSAGE.getMessage());
        }
        return amount / LOTTO_PRICE;
    }
}
