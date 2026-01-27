package gratitude.gratitude_log.controller.api;

import gratitude.gratitude_log.dto.ApiResponse;
import gratitude.gratitude_log.exception.GratitudeLogNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice(basePackages = "gratitude.gratitude_log.controller.api")
public class ApiExceptionHandler {

//    @ExceptionHandler(GratitudeLogNotFoundException.class)
//    public ResponseEntity<ApiResponse<Void>> handleNotFound(GratitudeLogNotFoundException e) {
//        return ResponseEntity.status(404).body(new ApiResponse<>(false, e.getMessage(), null));
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<Void>> handleAny(Exception e) {
//        return ResponseEntity.status(500).body(new ApiResponse<>(false, "サーバーエラーが発生しました。", null));
//    }
}
