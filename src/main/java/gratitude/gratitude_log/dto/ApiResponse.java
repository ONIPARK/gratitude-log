package gratitude.gratitude_log.dto;

public record ApiResponse<T> (boolean success, String message, T data) {

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "", data);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(false, message, null);
    }
}

//public final class ApiResponse {
//    private final boolean success;
//    private final String message;
//
//    public ApiResponse(boolean success, String message) {
//        this.success = success;
//        this.message = message;
//    }
//
//    public boolean success() {
//        return success;
//    }
//
//    public String message() {
//        return message;
//    }
//}