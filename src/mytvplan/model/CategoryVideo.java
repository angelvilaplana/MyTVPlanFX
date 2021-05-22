package mytvplan.model;

import java.util.Arrays;

public enum CategoryVideo implements InterfaceData {

    ALL("all"),
    COMEDY("comedy"),
    THRILLER("thriller"),
    ADVENTURES("adventures"),
    OTHER("other");

    private final String name;

    CategoryVideo(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return name;
    }

    public static CategoryVideo getValue(String value) {
        return Arrays.stream(CategoryVideo.values()).filter(m -> m.name.equals(value)).findAny().orElse(null);
    }

}
