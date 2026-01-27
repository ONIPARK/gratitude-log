package gratitude.gratitude_log.controller.api;

import gratitude.gratitude_log.dto.*;
import gratitude.gratitude_log.service.GratitudeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gratitude")
public class GratitudeLogApiController {

    private final GratitudeLogService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GratitudeLogCreateResponse create(@RequestBody GratitudeLogCreateRequest req) {
        Long id = service.create(req);
        return new GratitudeLogCreateResponse(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {

        service.delete(id); // 204: 削除成功

    }

    @GetMapping
    public List<GratitudeLogListDto> list(GratitudeLogSearchCond cond) {
        return service.findAll(cond);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable("id") Long id) {
//        service.delete(id);
//        return ResponseEntity.ok(new ApiResponse<>(true, "削除されました。",null));
//    }

//    @GetMapping
//    public ResponseEntity<ApiResponse<List<GratitudeLogListDto>>> list(GratitudeLogSearchCond cond) {
//        //List<GratitudeLogListDto> logs = service.findAll(cond);
//        //return ResponseEntity.ok(new ApiResponse<>(true, "", logs));
//        return ResponseEntity.ok(ApiResponse.ok(service.findAll(cond)));
//    }



}
