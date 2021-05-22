package mytvplan.model;

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

}
