package com.example.dms.data;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse {
    private String message;
    private int statusCode;
    private boolean success;
    private Object data;

    public GeneralResponse success(Object result) {
        this.statusCode = 200;
        this.success = true;
        this.message = "Success";
        this.data = result;
        return this;
    }

    public GeneralResponse failure(Object result) {
        this.statusCode = 400;
        this.success = false;
        this.message = "Failure";
        this.data = result;
        return this;
    }
}

