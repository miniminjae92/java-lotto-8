package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.dto.LottoDto;

public class Lottos {

    private final List<Lotto> tickets;

    public Lottos(List<Lotto> tickets) {
        this.tickets = tickets;
    }

    public Map<Rank, Integer> calculateStatistics(WinningLotto winningLotto) {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }

        for (Lotto lotto : tickets) {
            Rank rank = winningLotto.calculateRank(lotto);
            statistics.put(rank, statistics.get(rank) + 1);
        }
        return statistics;
    }

    public List<LottoDto> toDtoList() {
        return tickets.stream()
                .map(e -> new LottoDto(e.getNumbers()))
                .toList();
    }

    public int getSize() {
        return tickets.size();
    }
}
