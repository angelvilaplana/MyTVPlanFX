package mytvplan.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public abstract class BaseResponse {

    private boolean ok;

    private String errorMessage;

    public BaseResponse(String response) {
        setResult(response);
    }

    private void setResult(String response) {
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        ok = jsonObject.get("ok").getAsBoolean();

        if (ok) {
            setResponse(jsonObject);
            return;
        }

        this.errorMessage = jsonObject.get("result").getAsJsonObject().get("message").getAsString();
    }

    protected abstract void setResponse(JsonObject jsonObject);

    public boolean isOk() {
        return ok;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
