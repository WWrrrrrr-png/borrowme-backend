package com.borrowme.helper;

import com.borrowme.common.ApiResponse;
import com.borrowme.helper.response.HelperRequestResponse;
import com.borrowme.helper.response.HelperMatchingResponse;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/helper")
public class HelperController {

    private final HelperService helperService;

    public HelperController(HelperService helperService) {
        this.helperService = helperService;
    }

    @GetMapping("/requests")
    public ApiResponse<?> getApprovedRequests() {
        List<HelperRequestResponse> responseList = helperService.getApprovedRequests();
        return ApiResponse.success(responseList);
    }

    @PostMapping("/requests/{id}/accept")
    public ApiResponse<?> acceptRequest(@PathVariable Long id, HttpServletRequest httpRequest) {
        Long helperId = (Long) httpRequest.getAttribute("memberId");
        HelperMatchingResponse response = helperService.acceptRequest(helperId, id);
        return ApiResponse.success(response);
    }
}