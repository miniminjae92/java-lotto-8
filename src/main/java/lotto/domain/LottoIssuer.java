package lotto.domain;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class LottoIssuer {

    private final Supplier<List<Integer>> randomNumbers;

    public LottoIssuer(Supplier<List<Integer>> randomNumbers) {
        this.randomNumbers = randomNumbers;
    }

    public Lottos issue(int count) {
        List<Lotto> tickets = IntStream.range(0, count).mapToObj(i -> new Lotto(randomNumbers.get())).toList();
        return new Lottos(tickets);
    }
}
