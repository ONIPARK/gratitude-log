package gratitude.gratitude_log.service;

import gratitude.domain.GratitudeLog;
import gratitude.gratitude_log.dto.*;

import java.util.List;

public interface GratitudeLogService {

    Long create(GratitudeLogCreateRequest req);

    //List<GratitudeLogListDto> findAllForList();

    GratitudeLogEditResponse findForEdit(Long id);
    void update(GratitudeLogEditRequest req);

    void delete(Long id);

    List<GratitudeLogListDto> findAll();
    List<GratitudeLogListDto> findAll(GratitudeLogSearchCond cond);
}
