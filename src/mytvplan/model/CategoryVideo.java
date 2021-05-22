package mytvplan.model;

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

}
