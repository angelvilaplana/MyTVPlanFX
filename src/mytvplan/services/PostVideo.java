package mytvplan.services;

import mytvplan.model.BaseResponse;
import mytvplan.model.Video;
import mytvplan.utils.ServiceUtils;

import java.io.IOException;

public class PostVideo {

    public static BaseResponse execute(Video video) throws IOException {
        return ServiceUtils.execute("/videos", ServiceUtils.Method.POST, video.getJSON().toString());
    }

}
