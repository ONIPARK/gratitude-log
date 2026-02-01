package gratitude.gratitude_log.service;

import gratitude.domain.GratitudeLog;
import gratitude.gratitude_log.dto.*;
import gratitude.gratitude_log.exception.GratitudeLogNotFoundException;
import gratitude.gratitude_log.repository.GratitudeLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GratitudeLogServiceImpl implements GratitudeLogService{

    private final GratitudeLogMapper gratitudeLogMapper;

//    public GratitudeLogServiceImpl(GratitudeLogMapper gratitudeLogMapper) {
//        this.gratitudeLogMapper = gratitudeLogMapper;
//    }

    @Override
    public Long create(GratitudeLogCreateRequest req) {
        GratitudeLog log = GratitudeLog.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .build();

        gratitudeLogMapper.insert(log);
        return log.getId();
    }

//    @Override
//    public List<GratitudeLogListDto> findAllForList() {
//        List<GratitudeLog> logs = gratitudeLogMapper.selectAll();
//
//        return logs.stream()                        // logsをひとつづつ取り出す準備(stream 生成)
//                .map(l ->               // 各要素 l(GratitudeLog)をDTOに変換
//                        GratitudeLogListDto.builder()
//                                .id(l.getId())
//                                .title(l.getTitle())
//                                .content(l.getContent())
//                                .createdAt(l.getCreatedAt())
//                                .build()
//                )
//                .toList();                          // 変換されたものをListで集める


//        List<GratitudeLogListDto> result = new ArrayList<>();
//        for (GratitudeLog l : logs) {
//            GratitudeLogListDto dto = GratitudeLogListDto.builder()
//                    .id(l.getId())
//                    .title(l.getTitle())
//                    .createAt(l.getCreatedAt())
//                    .build();
//
//            result.add(dto);
//        }
//        return result;
//    }

//    @Override
//    public GratitudeLogEditRequest findForEdit(Long id) {
//        GratitudeLogEditRequest req = gratitudeLogMapper.findForEdit(id);
//        if (req == null) {
//            throw new IllegalStateException("存在しないID: " + id);
//        }
//        return req;
//    }

    @Override
    public GratitudeLogEditResponse findForEdit(Long id) {
        GratitudeLog log = gratitudeLogMapper.findForEdit(id);

        if (log == null) {

            //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "存在しないIDです。" + id);
            throw new GratitudeLogNotFoundException(id);
        }
        return new GratitudeLogEditResponse(
                log.getId(),
                log.getTitle(),
                log.getContent()
        );
    }

    @Override
    public void update(GratitudeLogEditRequest req) {
        int updated = gratitudeLogMapper.update(req);
        if (updated == 0) {
            throw new GratitudeLogNotFoundException(req.getId());
        }
    }

    @Override
    public void delete(Long id) {
        int deleted = gratitudeLogMapper.delete(id);
        //if (deleted == 0) throw new IllegalStateException("存在しないID: " + id);
        // API 対応
        if (deleted == 0) {
            throw new GratitudeLogNotFoundException(id);
        }

    }

    @Override
    public List<GratitudeLogListDto> findAll() {
        return findAll(new GratitudeLogSearchCond());
    }

    @Override
    public List<GratitudeLogListDto> findAll(GratitudeLogSearchCond cond) {
        // keyword trim
        if (cond.getKeyword() != null) {
            String k = cond.getKeyword().trim();
            cond.setKeyword(k.isEmpty() ? null : k);
        }

        return gratitudeLogMapper.findAll(cond);
    }
}
