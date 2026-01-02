package gratitude.gratitude_log.repository;

import gratitude.gratitude_log.domain.GratitudeLog;
import gratitude.gratitude_log.dto.GratitudeLogEditRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GratitudeLogMapper {
    // Create
    int insert(GratitudeLog log);
    // Read
    List<GratitudeLog> selectAll();
    // Update
    GratitudeLogEditRequest findForEdit(Long id);
    int update(@Param("id") Long id, @Param("req") GratitudeLogEditRequest req);
}
