package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottosTest {

    private WinningLotto wl;

    @BeforeEach
    void setup() {
        this.wl = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
    }

    @DisplayName("로또 목록을 정답과 비교하여 정확한 통계를 반환한다.")
    @ParameterizedTest(name = "입력된 로또들의 등수 통계를 정확히 계산한다.")
    @MethodSource("provideLottosAndExpectedStats")
    void calculateStatisticsTest(List<Lotto> userLottos, Map<Rank, Integer> expectedStats) {
        Lottos lottos = new Lottos(userLottos);

        Map<Rank, Integer> actualStats = lottos.calculateStatistics(wl);

        assertThat(actualStats).isEqualTo(expectedStats);
    }

    private static Stream<Arguments> provideLottosAndExpectedStats() {
        // [테스트 케이스 1] 1등 1개
        List<Lotto> list1 = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        Map<Rank, Integer> map1 = createExpectedMap(1, 0, 0, 0, 0, 0); // (1등, 2등, 3등, 4등, 5등, 꽝)

        // [테스트 케이스 2] 5등 1개, 4등 1개, 꽝 1개
        List<Lotto> list2 = List.of(
                new Lotto(List.of(1, 2, 3, 10, 11, 12)), // 5등
                new Lotto(List.of(1, 2, 3, 4, 13, 14)), // 4등
                new Lotto(List.of(10, 11, 12, 13, 14, 15)) // 꽝
        );
        Map<Rank, Integer> map2 = createExpectedMap(0, 0, 0, 1, 1, 1);

        // [테스트 케이스 3] 2등 1개, 3등 1개
        List<Lotto> list3 = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 7)), // 2등 (보너스 일치)
                new Lotto(List.of(1, 2, 3, 4, 5, 8))  // 3등 (보너스 불일치)
        );
        Map<Rank, Integer> map3 = createExpectedMap(0, 1, 1, 0, 0, 0);

        // [테스트 케이스 4] 빈 리스트
        List<Lotto> list4 = List.of();
        Map<Rank, Integer> map4 = createExpectedMap(0, 0, 0, 0, 0, 0); // 모두 0개

        // 7. 4개의 테스트 케이스를 반환
        return Stream.of(
                Arguments.of(list1, map1),
                Arguments.of(list2, map2),
                Arguments.of(list3, map3),
                Arguments.of(list4, map4)
        );
    }

    private static Map<Rank, Integer> createExpectedMap(
            int first, int second, int third, int fourth, int fifth, int miss
    ) {
        Map<Rank, Integer> map = new EnumMap<>(Rank.class);
        map.put(Rank.FIRST, first);
        map.put(Rank.SECOND, second);
        map.put(Rank.THIRD, third);
        map.put(Rank.FOURTH, fourth);
        map.put(Rank.FIFTH, fifth);
        map.put(Rank.MISS, miss);
        return map;
    }
}
