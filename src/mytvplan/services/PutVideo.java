package mytvplan.services;

import com.google.gson.JsonObject;
import mytvplan.model.BaseResponse;
import mytvplan.model.Video;
import mytvplan.model.BaseRequest;
import mytvplan.utils.ServiceUtils;

import java.io.IOException;

public class PutVideo extends BaseResponse {

    private Video video;

    public PutVideo(Video video) throws IOException {
        super(BaseRequest.execute("/videos/" + video.getId(), BaseRequest.Method.PUT, video.getJSON().toString()));
    }

    @Override
    protected void setResponse(JsonObject jsonObject) {
        JsonObject item = jsonObject.get("result").getAsJsonObject();
        video = ServiceUtils.getVideoJSON(item);
    }

    public Video getVideo() {
        return video;
    }

}
