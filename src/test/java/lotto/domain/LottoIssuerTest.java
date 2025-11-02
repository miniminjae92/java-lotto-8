package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoIssuerTest {

    @DisplayName("LottoIssuer는 주입된 Supplier를 요청된 개수만큼 호출한다.")
    @Test
    void issueTest() {
        Supplier<List<Integer>> stubSupplier = () -> List.of(6, 5, 4, 3, 2, 1);
        LottoIssuer issuer = new LottoIssuer(stubSupplier);
        int count = 3;

        Lottos lottos = issuer.issue(count);

        assertThat(lottos.getSize()).isEqualTo(3);
    }
}
