//package gratitude.gratitude_log.service;
//
//import gratitude.gratitude_log.domain.GratitudeLog;
//import gratitude.gratitude_log.repository.GratitudeLogMapper;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;
//
//@TestConfiguration
//public class TestBean {
//
//    @Bean
//    public GratitudeLogMapper gratitudeLogMapper() {
//        return new FakeGratitudeLogMapper();
//    }
//
//    @Bean
//    public GratitudeLogService gratitudeLogService(GratitudeLogMapper mapper) {
//        return new GratitudeLogServiceImpl(mapper);
//    }
//
//    static class FakeGratitudeLogMapper implements GratitudeLogMapper {
//        private final AtomicLong seq = new AtomicLong(0);
//        GratitudeLog lastInserted;
//        private final List<GratitudeLog> store = new ArrayList<>();
//
//        @Override
//        public int insert(GratitudeLog log) {
//            this.lastInserted = log;
//            log.setId(seq.incrementAndGet()); // DBが自動的に生成(仮定)
//
//            if (log.getCreatedAt() == null) {
//                log.setCreatedAt(LocalDateTime.now());
//            }
//            store.add(log);
//            return 1;
//        }
//
//        @Override
//        public List<GratitudeLog> selectAll() {
//            return new ArrayList<>(store);
//        }
//    }
//}
