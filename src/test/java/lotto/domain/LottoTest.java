package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("범위를 벗어난 로또 넘버가 존재하면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideInvalidLottoNumbers")
    void 범위를_벗어난_로또_넘버가_존재하면_예외가_발생한다(List<Integer> numbers) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(numbers));
    }

    private static Stream<Arguments> provideInvalidLottoNumbers() {
        return Stream.of(
                Arguments.of(List.of(0, 1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, -1, 5, 6)),
                Arguments.of(List.of(46, 2, 3, 1, 5, 6))
        );
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("다른 로또와 비교해서 일치하는 숫자의 갯수를 반환한다.")
    @Test
    void countMatchTest() {
        Lotto user = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto other1 = new Lotto(List.of(6, 5, 4, 3, 2, 1));    // 6
        Lotto other2 = new Lotto(List.of(7, 8, 9, 10, 12, 11)); // 0
        Lotto other3 = new Lotto(List.of(6, 8, 9, 10, 12, 11)); // 1

        assertThat(user.countMatch(other1)).isEqualTo(6);
        assertThat(user.countMatch(other2)).isEqualTo(0);
        assertThat(user.countMatch(other3)).isEqualTo(1);
    }

    @DisplayName("로또에 해당 숫자 포함 여부를 알려준다.")
    @Test
    void containsTest() {
        Lotto user = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(user.contains(1)).isTrue();
        assertThat(user.contains(45)).isFalse();
    }

    @DisplayName("오름차순 정렬된 순서를 가진다.")
    @Test
    void sortedTest() {
        Lotto user = new Lotto(List.of(6, 5, 2, 3, 1, 4));

        List<Integer> userNumbers = user.getNumbers();

        assertThat(userNumbers).containsExactlyElementsOf(List.of(1, 2, 3, 4, 5, 6));
    }
}
