package com.bbvacontinental.productosbbva.io.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johnlopezvega on 7/04/18.
 */

public class ProductResponse {

    @SerializedName("codProd")
    private String code;

    @SerializedName("NombreProd")
    private String name;

    @SerializedName("Descripcion")
    private String description;

    @SerializedName("cantidad")
    private String quantity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
