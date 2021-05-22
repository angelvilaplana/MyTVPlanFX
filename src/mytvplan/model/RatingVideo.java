package mytvplan.model;

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

}
