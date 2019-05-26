package dmitriy.blanutsa.coursework.controller.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApiResponse {

    private boolean success;
    private String message;

    public ApiResponse(Boolean success) {
        this.success = success;
    }

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
