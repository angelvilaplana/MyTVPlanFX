package mytvplan.services;

import com.google.gson.JsonObject;
import mytvplan.model.*;
import mytvplan.model.BaseRequest;
import mytvplan.utils.ServiceUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetVideo extends BaseResponse {

    private List<Video> videos;

    public GetVideo() throws IOException {
        super(BaseRequest.execute("/videos", BaseRequest.Method.GET));
    }

    public GetVideo(String path) throws IOException {
        super(BaseRequest.execute("/videos" + path, BaseRequest.Method.GET));
    }

    @Override
    protected void setResponse(JsonObject jsonObject) {
        this.videos = new ArrayList<>();

        jsonObject.get("result").getAsJsonArray().forEach(item -> {
            videos.add(ServiceUtils.getVideoJSON(item.getAsJsonObject()));
        });
    }

    public List<Video> getResponse() {
        return videos;
    }

}

