package gratitude.gratitude_log.repository;

import gratitude.domain.GratitudeLog;
import gratitude.gratitude_log.dto.GratitudeLogEditRequest;
import gratitude.gratitude_log.dto.GratitudeLogListDto;
import gratitude.gratitude_log.dto.GratitudeLogSearchCond;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GratitudeLogMapper {
    // Create
    int insert(GratitudeLog log);

    // Read
    List<GratitudeLog> selectAll();

    // Update
    GratitudeLogEditRequest findForEdit(Long id);
    //int update(@Param("id") Long id, @Param("req") GratitudeLogEditRequest req);
    int update(GratitudeLogEditRequest req);

    // Delete
    int delete(@Param("id") Long id);

    // 整列
    List<GratitudeLogListDto> findAll(GratitudeLogSearchCond cond);
}
