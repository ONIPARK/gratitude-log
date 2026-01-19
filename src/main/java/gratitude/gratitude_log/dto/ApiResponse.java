package gratitude.gratitude_log.dto;

public record ApiResponse(boolean success, String message) {}

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