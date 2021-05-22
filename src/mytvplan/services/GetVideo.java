package mytvplan.services;

import mytvplan.model.*;
import mytvplan.utils.ServiceUtils;

import java.io.IOException;

public class GetVideo {

    public static BaseResponse execute() throws IOException {
        return ServiceUtils.execute("/videos", ServiceUtils.Method.GET);
    }

}
