package dev.checku.checkuscheduler.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    NOTIFICATION_FAILED(400, "알림 전송에 실패하였습니다."),
    SUBSCRIBE_FAILED(400, "구독에 실패하였습니다."),
    UNSUBSCRIBE_FAILED(400, "구독 취소에 실패하였습니다."),


    USER_NOT_FOUND(400, "해당 회원은 존재하지 않습니다."),

    TOPIC_NOT_FOUND(400, "해당 토픽은 존재하지 않습니다"),

    NOTIFICATION_NOT_FOUND(400, "해당 알림은 존재하지 않습니다."),
    ALREADY_APPLIED_NOTIFICATION(400, "해당 과목은 이미 신청하였습니다."),

    SUBJECT_NOT_FOUND(400, "해당 과목을 찾을 수 없습니다."),
    MY_SUBJECT_NOT_FOUND(400, "해당 과목을 찾을 수 없습니다."),
    HAVA_A_VACANCY(400, "해당 과목은 빈 자리가 남아있습니다."),
    // 세션
    SESSION_NOT_FOUND(400, "해당 세션을 찾을 수 없습니다.");


    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;

}

