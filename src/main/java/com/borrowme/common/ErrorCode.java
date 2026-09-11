package com.borrowme.common;

public enum ErrorCode {

    EMAIL_DUPLICATE(409, "이미 사용 중인 이메일입니다."),
    LOGIN_FAILED(401, "이메일 또는 비밀번호가 올바르지 않습니다."),
    USER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    HELPER_NOT_FOUND(404, "존재하지 않는 헬퍼입니다."),
    REQUEST_NOT_FOUND(404, "존재하지 않는 요청입니다."),
    REQUEST_NOT_APPROVED(400, "승인되지 않은 요청은 매칭할 수 없습니다."),
    MATCHING_NOT_FOUND(404, "존재하지 않는 매칭입니다."),
    PAYMENT_NOT_FOUND(404, "존재하지 않는 결제입니다."),
    PAYMENT_FAILED(500, "카카오페이 결제 처리 중 오류가 발생했습니다."),
    AMOUNT_NOT_SET(400, "관리자가 아직 결제 금액을 설정하지 않았습니다."),
    ACCESS_DENIED(403, "접근 권한이 없습니다."),
    INVALID_INPUT(400, "입력값이 올바르지 않습니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() { return status; }
    public String getMessage() { return message; }
}