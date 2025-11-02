package lotto.domain;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

public enum Rank {
    FIFTH(3, 5_000, false, "3개 일치"),
    FOURTH(4, 50_000, false, "4개 일치"),
    THIRD(5, 1_500_000, false, "5개 일치"),
    SECOND(5, 30_000_000, true, "5개 일치, 보너스 볼 일치"),
    FIRST(6, 2_000_000_000, false, "6개 일치"),
    MISS(0, 0, false, "꽝");

    private final int countMatch;
    private final int prize;
    private final boolean needBonus;
    private final String description;

    Rank(int countMatch, int prize, boolean needBonus, String description) {
        this.countMatch = countMatch;
        this.prize = prize;
        this.needBonus = needBonus;
        this.description = description;
    }

    public static Rank valueOf(int matchCounts, boolean hasBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.countMatch == matchCounts)
                .filter(rank -> rank.needBonus == hasBonus)
                .findFirst()
                .orElse(MISS);
    }

    public int getPrize() {
        return prize;
    }

    public String getMessage() {
        NumberFormat nf = NumberFormat.getInstance(Locale.KOREA);
        String formattedPrize = nf.format(this.prize);
        return String.format("%s (%s원)", this.description, formattedPrize);
    }
}
