package gratitude.gratitude_log.service;

import gratitude.gratitude_log.dto.GratitudeLogCreateRequest;

public interface GratitudeLogService {
    Long create(GratitudeLogCreateRequest req);
}
