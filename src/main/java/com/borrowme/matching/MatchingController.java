package com.borrowme.matching;

import com.borrowme.common.ApiResponse;
import com.borrowme.matching.request.MatchingStatusUpdateRequest;
import com.borrowme.matching.response.MatchingResponse;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matchings")
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping("/my")   
    public ApiResponse<?> getMyMatchings(HttpServletRequest httpRequest) {
        Long memberId = (Long) httpRequest.getAttribute("memberId");
        String role = (String) httpRequest.getAttribute("memberRole");

        List<MatchingResponse> response;
        if ("HELPER".equals(role)) {
            response = matchingService.getMyMatchingsAsHelper(memberId);
        } else {
            response = matchingService.getMyMatchingsAsUser(memberId);
        }
        return ApiResponse.success(response);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getMatchingDetail(@PathVariable Long id) {
        MatchingResponse response = matchingService.getMatchingDetail(id);
        return ApiResponse.success(response);
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<?> updateMatchingStatus(@PathVariable Long id,
                                                @RequestBody MatchingStatusUpdateRequest request) {
        MatchingResponse response = matchingService.updateMatchingStatus(id, request);
        return ApiResponse.success(response);
    }

}