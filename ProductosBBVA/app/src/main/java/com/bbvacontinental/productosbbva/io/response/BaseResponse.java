package com.bbvacontinental.productosbbva.io.response;

import com.bbvacontinental.productosbbva.io.ApiConstants;
import com.google.gson.annotations.SerializedName;

/**
 * Created by johnlopezvega on 7/04/18.
 */

public class BaseResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("respStatus")
    private String respStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRespStatus() {
        return respStatus;
    }

    public void setRespStatus(String respStatus) {
        this.respStatus = respStatus;
    }

    public boolean isSuccessful() {
        return ApiConstants.OK.equalsIgnoreCase(status);
    }
}
