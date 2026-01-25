package gratitude.gratitude_log.controller.api;

import gratitude.gratitude_log.dto.ApiResponse;
import gratitude.gratitude_log.dto.GratitudeLogListDto;
import gratitude.gratitude_log.dto.GratitudeLogSearchCond;
import gratitude.gratitude_log.service.GratitudeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gratitude")
public class GratitudeLogApiController {

    private final GratitudeLogService service;

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "削除されました。",null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GratitudeLogListDto>>> list(GratitudeLogSearchCond cond) {
        //List<GratitudeLogListDto> logs = service.findAll(cond);
        //return ResponseEntity.ok(new ApiResponse<>(true, "", logs));
        return ResponseEntity.ok(ApiResponse.ok(service.findAll(cond)));
    }



}
