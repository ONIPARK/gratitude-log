package gratitude.gratitude_log.controller.api;

import gratitude.gratitude_log.dto.ApiResponse;
import gratitude.gratitude_log.service.GratitudeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gratitude")
public class GratitudeLogApiController {

    private final GratitudeLogService service;

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "削除されました。"));
    }
}
