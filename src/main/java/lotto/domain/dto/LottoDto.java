package lotto.domain.dto;

import java.util.List;
import java.util.Objects;

public final class LottoDto {
    private final List<Integer> numbers;

    public LottoDto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> numbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (LottoDto) obj;
        return Objects.equals(this.numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        return "LottoDto[" +
                "numbers=" + numbers + ']';
    }
}
