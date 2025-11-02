package lotto.domain.dto;

import java.util.Objects;

public final class StatisticsDto {
    private final String rankMessage;
    private final int count;

    public StatisticsDto(String rankMessage, int count) {
        this.rankMessage = rankMessage;
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (StatisticsDto) obj;
        return Objects.equals(this.rankMessage, that.rankMessage) &&
                this.count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankMessage, count);
    }

    @Override
    public String toString() {
        return String.format("%s - %d개", rankMessage, count);
    }
}
