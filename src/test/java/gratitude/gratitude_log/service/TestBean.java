package gratitude.gratitude_log.service;

import gratitude.gratitude_log.domain.GratitudeLog;
import gratitude.gratitude_log.repository.GratitudeLogMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class TestBean {

    @Bean
    public GratitudeLogMapper gratitudeLogMapper() {
        return new FakeGratitudeLogMapper();
    }

    @Bean
    public GratitudeLogService gratitudeLogService(GratitudeLogMapper mapper) {
        return new GratitudeLogServiceImpl(mapper);
    }

    static class FakeGratitudeLogMapper implements GratitudeLogMapper {
        private final AtomicLong seq = new AtomicLong(0);
        GratitudeLog lastInserted;

        @Override
        public int insert(GratitudeLog log) {
            this.lastInserted = log;
            log.setId(seq.incrementAndGet()); // DBが自動的に生成(仮定)
            return 1;
        }
    }
}
