package mytvplan.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class BaseResponse {

    private boolean ok;

    private List<Video> videos;

    private String errorMessage;

    public BaseResponse(String response) {
        setResult(response);
    }

    private void setResult(String response) {
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        ok = jsonObject.get("ok").getAsBoolean();

        if (ok) {
            setVideos(jsonObject);
            return;
        }

        this.errorMessage = jsonObject.get("result").getAsJsonObject().get("message").getAsString();
    }

    private void setVideos(JsonObject jsonObject) {
        this.videos = new ArrayList<>();

        jsonObject.get("result").getAsJsonArray().forEach(item -> {
            String id = item.getAsJsonObject().get("_id").getAsString();
            String title = item.getAsJsonObject().get("title").getAsString();
            TypeVideo typeVideo = TypeVideo.getValue(item.getAsJsonObject().get("type").getAsString());
            PlatformVideo platformVideo = PlatformVideo.getValue(item.getAsJsonObject().get("platform").getAsString());
            CategoryVideo categoryVideo = CategoryVideo.getValue(item.getAsJsonObject().get("category").getAsString());
            RatingVideo ratingVideo = RatingVideo.getValue(item.getAsJsonObject().get("rating").getAsString());
             videos.add(new Video(id, title, typeVideo, platformVideo, categoryVideo, ratingVideo));
        });
    }

    public boolean isOk() {
        return ok;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
