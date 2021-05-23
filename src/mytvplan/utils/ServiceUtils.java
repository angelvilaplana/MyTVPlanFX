package mytvplan.utils;

import com.google.gson.JsonObject;
import mytvplan.model.*;
import mytvplan.services.DeleteVideo;
import mytvplan.services.GetVideo;
import mytvplan.services.PostVideo;
import mytvplan.services.PutVideo;

import java.io.IOException;

public class ServiceUtils {

    public static Video getVideoJSON(JsonObject video) {
        String id = video.getAsJsonObject().get("_id").getAsString();
        String title = video.getAsJsonObject().get("title").getAsString();
        TypeVideo typeVideo = TypeVideo.getValue(video.getAsJsonObject().get("type").getAsString());
        PlatformVideo platformVideo = PlatformVideo.getValue(video.getAsJsonObject().get("platform").getAsString());
        CategoryVideo categoryVideo = CategoryVideo.getValue(video.getAsJsonObject().get("category").getAsString());
        RatingVideo ratingVideo = RatingVideo.getValue(video.getAsJsonObject().get("rating").getAsString());

        return new Video(id, title, typeVideo, platformVideo, categoryVideo, ratingVideo);
    }

    public static GetVideo getVideos(String path) throws IOException {
        return new GetVideo(path);
    }

    public static DeleteVideo deleteVideo(Video video) throws IOException {
        return new DeleteVideo(video);
    }

    public static PostVideo saveVideo(Video video) throws IOException {
        video.setId(null);
        return new PostVideo(video);
    }

    public static PutVideo updateVideo(Video video) throws IOException {
        return new PutVideo(video);
    }

}
