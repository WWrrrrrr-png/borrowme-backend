package com.borrowme.matching.response;

import java.time.LocalDateTime;

import com.borrowme.matching.dto.MatchingDto;

public class MatchingResponse {

    private Long id;
    private Long requestId;
    private Long helperId;
    private String status;
    private LocalDateTime createdAt;

    public MatchingResponse(Long id, Long requestId, Long helperId, String status, LocalDateTime createdAt) {
        this.id = id;
        this.requestId = requestId;
        this.helperId = helperId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static MatchingResponse from(MatchingDto dto) {
        return new MatchingResponse(
                dto.getId(),
                dto.getRequestId(),
                dto.getHelperId(),
                dto.getStatus(),
                dto.getCreatedAt()
        );
    }

    public Long getId() {
        return id;
    }

    public Long getRequestId() {
        return requestId;
    }

    public Long getHelperId() {
        return helperId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
