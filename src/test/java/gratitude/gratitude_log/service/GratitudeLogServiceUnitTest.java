package gratitude.gratitude_log.service;

import gratitude.gratitude_log.dto.GratitudeLogCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class GratitudeLogServiceUnitTest {

    @Test
    void create_returns_id() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
        GratitudeLogService service = ac.getBean(GratitudeLogService.class);

        // given
        GratitudeLogCreateRequest req = GratitudeLogCreateRequest.builder()
                .title("title")
                .content("content")
                .build();

        // when
        Long id = service.create(req);

        // then
        assertEquals(1L, id);

    }

}