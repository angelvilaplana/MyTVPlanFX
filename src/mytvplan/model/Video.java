package mytvplan.model;

import com.google.gson.JsonObject;

public class Video {

    private String id;

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

    public Video(String id, String title, TypeVideo type, PlatformVideo platform, CategoryVideo category, RatingVideo rating) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.platform = platform;
        this.category = category;
        this.rating = rating;
    }

    public JsonObject getJSON() {
        JsonObject jsonObject = new JsonObject();
        if (id != null) {
            jsonObject.addProperty("_id", id);
        }
        jsonObject.addProperty("title", title);
        jsonObject.addProperty("type", type.getValue());
        jsonObject.addProperty("platform", platform.getValue());
        jsonObject.addProperty("category", category.getValue());
        jsonObject.addProperty("rating", rating.getValue());
        return jsonObject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public TypeVideo getType() {
        return type;
    }

    public PlatformVideo getPlatform() {
        return platform;
    }

    public CategoryVideo getCategory() {
        return category;
    }

    public RatingVideo getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return title;
    }

}
