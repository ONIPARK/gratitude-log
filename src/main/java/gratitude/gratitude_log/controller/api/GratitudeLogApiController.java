package gratitude.gratitude_log.controller.api;

import gratitude.gratitude_log.dto.*;
import gratitude.gratitude_log.service.GratitudeLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gratitude")
public class GratitudeLogApiController {

    private final GratitudeLogService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public GratitudeLogCreateResponse create(@RequestBody GratitudeLogCreateRequest req) {
        Long id = service.create(req);
        return new GratitudeLogCreateResponse(id);
    }

    @GetMapping("/{id}") // 200
    public GratitudeLogEditResponse editForm(@PathVariable("id") Long id) {
        return service.findForEdit(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void update(@Valid @RequestBody GratitudeLogEditRequest req) {

//        if (req == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "リストが空です。");
//        }
//        String title = req.getTitle() == null ? "" : req.getTitle().trim();
//        String content = req.getContent() == null ? "" : req.getContent().trim();
//
//        if (title.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "タイトルは必須です。");
//        }
//
//        if (title.length() > 100) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "タイトルは100文字以内で入力してください。");
//        }
//
//        if (content.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "内容は必須です。");
//        }

        service.update(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
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
