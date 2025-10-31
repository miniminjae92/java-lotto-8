package lotto.domain;

import java.util.List;
import lotto.constants.ErrorMessage;

//- Lotto (로또 1장)
//  - [ ] 6개의 번호를 가진다.
//  - [ ] 생성 시점에 스스로 유효성 검사(중복, 범위) 및 정렬을 수행한다.
//        - [ ] 다른 로또와 몇 개가 겹치는지(countMatch), 특정 번호를 포함하는지(contains) 계산할 수 있다.
public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_COUNT.getMessage());
        }
    }

    // TODO: 추가 기능 구현
}
