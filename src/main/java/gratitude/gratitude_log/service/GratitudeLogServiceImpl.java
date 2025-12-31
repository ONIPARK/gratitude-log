package gratitude.gratitude_log.service;

import gratitude.gratitude_log.domain.GratitudeLog;
import gratitude.gratitude_log.dto.GratitudeLogCreateRequest;
import gratitude.gratitude_log.repository.GratitudeLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GratitudeLogServiceImpl implements GratitudeLogService{

    private final GratitudeLogMapper mapper;

    public GratitudeLogServiceImpl(GratitudeLogMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Long create(GratitudeLogCreateRequest req) {
        GratitudeLog log = GratitudeLog.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .build();

        mapper.insert(log);
        return log.getId();
    }
}
