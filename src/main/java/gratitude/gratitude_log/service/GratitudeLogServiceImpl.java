package gratitude.gratitude_log.service;

import gratitude.gratitude_log.domain.GratitudeLog;
import gratitude.gratitude_log.dto.GratitudeLogCreateRequest;
import gratitude.gratitude_log.dto.GratitudeLogListDto;
import gratitude.gratitude_log.repository.GratitudeLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GratitudeLogServiceImpl implements GratitudeLogService{

    private final GratitudeLogMapper gratitudeLogMapper;

    public GratitudeLogServiceImpl(GratitudeLogMapper gratitudeLogMapper) {
        this.gratitudeLogMapper = gratitudeLogMapper;
    }

    @Override
    public Long create(GratitudeLogCreateRequest req) {
        GratitudeLog log = GratitudeLog.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .build();

        gratitudeLogMapper.insert(log);
        return log.getId();
    }

    @Override
    public List<GratitudeLogListDto> findAllForList() {
        List<GratitudeLog> logs = gratitudeLogMapper.selectAll();

        return logs.stream()                        // logsをひとつづつ取り出す準備(stream 生成)
                .map(l ->               // 各要素 l(GratitudeLog)をDTOに変換
                        GratitudeLogListDto.builder()
                                .id(l.getId())
                                .title(l.getTitle())
                                .createAt(l.getCreatedAt())
                                .build()
                )
                .toList();                          // 変換されたものをListで集める


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
    }
}
