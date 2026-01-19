package gratitude.gratitude_log.service;

import gratitude.gratitude_log.dto.GratitudeLogCreateRequest;
import gratitude.gratitude_log.dto.GratitudeLogEditRequest;
import gratitude.gratitude_log.dto.GratitudeLogListDto;

import java.util.List;

public interface GratitudeLogService {

    Long create(GratitudeLogCreateRequest req);

    List<GratitudeLogListDto> findAllForList();

    GratitudeLogEditRequest findForEdit(Long id);
    void update(GratitudeLogEditRequest req);

    void delete(Long id);
}
