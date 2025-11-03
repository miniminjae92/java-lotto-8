package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @DisplayName("일치 갯수와 보너스 여부에 따른 올바른 랭크를 반환한다.")
    @ParameterizedTest
    @MethodSource("provideRankCases")
    void valueOfTest(int countMatch, boolean hasBonus, Rank expected) {
        Rank actual = Rank.valueOf(countMatch, hasBonus);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideRankCases() {
        return Stream.of(
                Arguments.of(3, false, Rank.FIFTH),
                Arguments.of(4, false, Rank.FOURTH),
                Arguments.of(5, false, Rank.THIRD),
                Arguments.of(5, true, Rank.SECOND),
                Arguments.of(6, false, Rank.FIRST),
                Arguments.of(2, false, Rank.MISS),
                Arguments.of(2, true, Rank.MISS),
                Arguments.of(1, false, Rank.MISS),
                Arguments.of(0, false, Rank.MISS)
        );
    }

    @DisplayName("각 랭크는 올바른 상금을 반환한다.")
    @ParameterizedTest
    @MethodSource("providePrizeCases")
    void getPrizeTest(Rank rank, int expected) {
        assertThat(rank.getPrize()).isEqualTo(expected);
    }

    private static Stream<Arguments> providePrizeCases() {
        return Stream.of(
                Arguments.of(Rank.FIFTH, 5_000),
                Arguments.of(Rank.FOURTH, 50_000),
                Arguments.of(Rank.THIRD, 1_500_000),
                Arguments.of(Rank.SECOND, 30_000_000),
                Arguments.of(Rank.FIRST, 2_000_000_000),
                Arguments.of(Rank.MISS, 0)
        );
    }

    @DisplayName("각 랭크는 정확한 출력메세지를 반환한다.")
    @ParameterizedTest
    @MethodSource("provideMessageCases")
    void getPrizeTest(Rank rank, String expected) {
        assertThat(rank.getMessage()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideMessageCases() {
        return Stream.of(
                Arguments.of(Rank.FIFTH, "3개 일치 (5,000원)"),
                Arguments.of(Rank.FOURTH, "4개 일치 (50,000원)"),
                Arguments.of(Rank.THIRD, "5개 일치 (1,500,000원)"),
                Arguments.of(Rank.SECOND, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
                Arguments.of(Rank.FIRST, "6개 일치 (2,000,000,000원)"),
                Arguments.of(Rank.MISS, "꽝 (0원)")
        );
    }
}
