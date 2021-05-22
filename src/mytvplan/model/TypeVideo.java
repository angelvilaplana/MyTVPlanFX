package mytvplan.model;

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

}
