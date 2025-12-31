package gratitude.gratitude_log.service;

import gratitude.gratitude_log.domain.GratitudeLog;
import gratitude.gratitude_log.dto.GratitudeLogCreateRequest;
import gratitude.gratitude_log.dto.GratitudeLogListDto;
import gratitude.gratitude_log.repository.GratitudeLogMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//@Import(TestBean.class)
class GratitudeLogServiceUnitTest {

    //@Autowired GratitudeLogService service;

    private GratitudeLogService service;
    private FakeGratitudeLogMapper fakeMapper;

    @BeforeEach
    void setUp() {
        fakeMapper = new FakeGratitudeLogMapper();
        service = new GratitudeLogServiceImpl(fakeMapper);
    }

    @Test
    void create_returns_id() {

//        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
//        GratitudeLogService service = ac.getBean(GratitudeLogService.class);

        // given
        GratitudeLogCreateRequest req = GratitudeLogCreateRequest.builder()
                .title("title")
                .content("content")
                .build();

        // when
        Long id = service.create(req);

        // then
        //assertEquals(1L, id);
        assertThat(id).isEqualTo(1L);
    }

    @Test
    void create_then_findAllForList_contains_item() {
        //given
        service.create(new GratitudeLogCreateRequest("感謝タイトル", "内容"));

        //when
        List<GratitudeLogListDto> list = service.findAllForList();

        //then
        assertThat(list).hasSize(1);
        assertThat(list.get(0).getTitle()).isEqualTo("感謝タイトル");
        assertThat(list.get(0).getId()).isEqualTo(1L);
    }

    static class FakeGratitudeLogMapper implements GratitudeLogMapper {
        private final AtomicLong seq = new AtomicLong(0);
        GratitudeLog lastInserted;
        private final List<GratitudeLog> store = new ArrayList<>();

        @Override
        public int insert(GratitudeLog log) {
            this.lastInserted = log;
            log.setId(seq.incrementAndGet()); // DBが自動的に生成(仮定)

            if (log.getCreatedAt() == null) {
                log.setCreatedAt(LocalDateTime.now());
            }
            store.add(log);
            return 1;
        }

        @Override
        public List<GratitudeLog> selectAll() {
            return new ArrayList<>(store);
        }
    }

}