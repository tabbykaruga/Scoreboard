package com.example.scoreboard.api;

import retrofit2.Response;

public class ApiResponseError extends Throwable {
    private Response response;

    public ApiResponseError(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}