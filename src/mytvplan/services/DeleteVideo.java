package mytvplan.services;

import com.google.gson.JsonObject;
import mytvplan.model.*;
import mytvplan.utils.ServiceUtils;

import java.io.IOException;

public class DeleteVideo extends BaseResponse {

    private Video video;

    public DeleteVideo(Video video) throws IOException {
        super(BaseRequest.execute("/videos/" + video.getId(), BaseRequest.Method.DELETE));
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
