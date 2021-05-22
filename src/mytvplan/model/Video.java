package mytvplan.model;

public class Video {

    private String title;

    private TypeVideo type;

    private PlatformVideo platform;

    private CategoryVideo category;

    private RatingVideo rating;

    public Video(String title, TypeVideo type, PlatformVideo platform, CategoryVideo category, RatingVideo rating) {
        this.title = title;
        this.type = type;
        this.platform = platform;
        this.category = category;
        this.rating = rating;
    }

}
