package com.borrowme.helper.response;

public class HelperMatchingResponse {

    private Long id;
    private String status;

    public HelperMatchingResponse(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}