package gratitude.gratitude_log.service;

import gratitude.gratitude_log.domain.GratitudeLog;
import gratitude.gratitude_log.dto.GratitudeLogEditRequest;
import gratitude.gratitude_log.repository.GratitudeLogMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class FakeGratitudeLogMapper implements GratitudeLogMapper {

    // id値
    private final AtomicLong seq = new AtomicLong(0);
    private final Map<Long, GratitudeLog> store = new LinkedHashMap<>();

    @Override
    public int insert(GratitudeLog log) {
        long id = seq.incrementAndGet();

        log.setId(id);
        if (log.getCreatedAt() == null) {
            log.setCreatedAt(LocalDateTime.now());
        }

        store.put(id, log);
        return 1;
    }

    // SELECT
    @Override
    public List<GratitudeLog> selectAll() {
        return new ArrayList<>(store.values());
    }

    // UPDATE
    @Override
    public GratitudeLogEditRequest findForEdit(Long id) {
        GratitudeLog log = store.get(id);
        if (log == null) return null;

        GratitudeLogEditRequest req = new GratitudeLogEditRequest();
        req.setTitle(log.getTitle());
        req.setContent(log.getContent());
        return req;
    }

    @Override
    public int update(GratitudeLogEditRequest req) {
        GratitudeLog log = store.get(req.getId());
        log.setTitle(req.getTitle());
        log.setContent(req.getContent());
        return 1;
    }

    @Override
    public int delete(Long id) {
        return store.remove(id) != null ? 1 : 0;
    }
}
