package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import lotto.common.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {

    @DisplayName("WinningLotto 생성자 테스트")
    @Nested
    class ConstructorTest {

        @DisplayName("유효한 당첨번호 6개와 보너스번호 1개로 생성이 성공한다.")
        @Test
        void createTest() {
            List<Integer> mainNumbers = List.of(1, 2, 3, 4, 5, 6);
            int bonusNumber = 7;

            assertThatCode(() -> new WinningLotto(mainNumbers, bonusNumber)).doesNotThrowAnyException();
        }

        @DisplayName("당첨번호와 보너스번호가 중복될 경우 예외를 생성한다.")
        @Test
        void duplicateTest() {
            List<Integer> mainNumbers = List.of(1, 2, 3, 4, 5, 6);
            int bonusNumber = 6;

            assertThatThrownBy(() -> new WinningLotto(mainNumbers, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.DUPLICATE_BONUS_NUMBER.getMessage());
        }

        @DisplayName("보너스 번호는 1~45 범위를 벗어나면 예외를 반환한다.")
        @Test
        void rangeTest() {
            List<Integer> mainNumbers = List.of(1, 2, 3, 4, 5, 6);
            int bonusNumber = 46;

            assertThatThrownBy(() -> new WinningLotto(mainNumbers, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    @DisplayName("Rank 등수 계산 테스트")
    @Nested
    class CalculateRankTest {

        private WinningLotto winningLotto;

        @BeforeEach
        void setup() {
            this.winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        }

        @DisplayName("유저 로또와 비교해서 등수를 반환한다.")
        @ParameterizedTest
        @MethodSource("provideLottoAndRank")
        void calculateRankTest(Lotto userLotto, Rank expected) {
            Rank actualRank = winningLotto.calculateRank(userLotto);

            assertThat(actualRank).isEqualTo(expected);
        }

        private static Stream<Arguments> provideLottoAndRank() {
            return Stream.of(
                    Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), Rank.FIRST),
                    Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)), Rank.SECOND),
                    Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 8)), Rank.THIRD),
                    Arguments.of(new Lotto(List.of(1, 2, 3, 4, 8, 9)), Rank.FOURTH),
                    Arguments.of(new Lotto(List.of(1, 2, 3, 8, 9, 10)), Rank.FIFTH),
                    Arguments.of(new Lotto(List.of(1, 2, 8, 9, 10, 11)), Rank.MISS),
                    Arguments.of(new Lotto(List.of(1, 12, 8, 9, 10, 11)), Rank.MISS),
                    Arguments.of(new Lotto(List.of(13, 12, 8, 9, 10, 11)), Rank.MISS)
            );
        }

    }


}
