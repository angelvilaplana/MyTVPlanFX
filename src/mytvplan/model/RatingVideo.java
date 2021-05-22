package mytvplan.model;

import java.util.Arrays;

public enum RatingVideo implements InterfaceData {

    ALL("all"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5");

    private final String rating;

    RatingVideo(String rating) {
        this.rating = rating;
    }

    @Override
    public String getValue() {
        return rating;
    }

    public static RatingVideo getValue(String value) {
        return Arrays.stream(RatingVideo.values()).filter(m -> m.rating.equals(value)).findAny().orElse(null);
    }

}
