package mytvplan.model;

import java.util.Arrays;

public enum PlatformVideo implements InterfaceData {

    ALL("all"),
    NETFLIX("Netflix"),
    HBO("HBO"),
    DISNEY("Disney+"),
    TV("TV");

    private final String name;

    PlatformVideo(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return name;
    }

    public static PlatformVideo getValue(String value) {
        return Arrays.stream(PlatformVideo.values()).filter(m -> m.name.equals(value)).findAny().orElse(null);
    }

}
