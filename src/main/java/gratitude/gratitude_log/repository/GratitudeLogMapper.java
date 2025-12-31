package gratitude.gratitude_log.repository;

import gratitude.gratitude_log.domain.GratitudeLog;

import java.util.List;

public interface GratitudeLogMapper {
    // 登録
    int insert(GratitudeLog log);
    // リスト
    List<GratitudeLog> selectAll();
}
