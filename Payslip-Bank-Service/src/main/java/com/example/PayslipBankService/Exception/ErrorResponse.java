package com.example.PayslipBankService.Exception;

import java.time.LocalDateTime;
import java.util.UUID;

public class ErrorResponse {

    private String uid = UUID.randomUUID().toString();

    private LocalDateTime timestamp = LocalDateTime.now();

    private int status;
    private String error;
    private String message;

    public ErrorResponse() {

    }

    public ErrorResponse(String uid, LocalDateTime timestamp, int status, String error, String message) {
        this.uid = uid;
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "uid='" + uid + '\'' +
                ", timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
