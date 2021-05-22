package mytvplan.model;

import java.util.Arrays;

public enum TypeVideo implements InterfaceData {

    ALL("all"),
    NETFLIX("Netflix"),
    HBO("HBO"),
    DISNEY("Disney+"),
    TV("TV");

    private final String name;

    TypeVideo(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return name;
    }

    public static TypeVideo getValue(String value) {
        return Arrays.stream(TypeVideo.values()).filter(m -> m.name.equals(value)).findAny().orElse(null);
    }

}
