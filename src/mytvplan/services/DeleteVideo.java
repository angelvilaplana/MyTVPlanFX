package mytvplan.services;

import mytvplan.model.BaseResponse;
import mytvplan.model.Video;
import mytvplan.utils.ServiceUtils;

import java.io.IOException;

public class DeleteVideo {

    public static BaseResponse execute(Video video) throws IOException {
        return ServiceUtils.execute("/videos/" + video.getId(), ServiceUtils.Method.DELETE);
    }

}
