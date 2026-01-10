package gratitude.gratitude_log.service;

import gratitude.gratitude_log.dto.GratitudeLogCreateRequest;
import gratitude.gratitude_log.dto.GratitudeLogEditRequest;
import gratitude.gratitude_log.dto.GratitudeLogListDto;
import gratitude.gratitude_log.repository.GratitudeLogMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GratitudeLogServiceTest {

    GratitudeLogService service;

    @BeforeEach
    void beforeEach() {
        GratitudeLogMapper fakeMapper = new FakeGratitudeLogMapper();
        service = new GratitudeLogServiceImpl(fakeMapper);
    }

    @Test
    void createするとidが変換される() {
        // given
        GratitudeLogCreateRequest req = new GratitudeLogCreateRequest();
        req.setTitle("title1");
        req.setContent("content1");
        
        // when
        Long id = service.create(req);

        // then
        assertThat(id).isNotNull();

        //System.out.println(id);
    }

    @Test
    void findAllForListはDTOで変換されて渡す() {
        // given
        GratitudeLogCreateRequest req1 = new GratitudeLogCreateRequest();
        req1.setTitle("title1");
        req1.setContent("content1");
        service.create(req1);

        GratitudeLogCreateRequest req2 = new GratitudeLogCreateRequest();
        req2.setTitle("title2");
        req2.setContent("content2");
        service.create(req2);

        // when
        List<GratitudeLogListDto> list = service.findAllForList();

//        for (GratitudeLogListDto dto : list) {
//            System.out.println(dto.getTitle());
//            System.out.println(dto.getCreatedAt());
//        }

        // then
        assertThat(list).hasSize(2);
        assertThat(list.get(1).getTitle()).isEqualTo("title2");
        assertThat(list.get(1).getCreatedAt()).isNotNull();
    }

    @Test
    void findForEdit_存在するIDはDTOを変換する() {
        //given
        GratitudeLogCreateRequest req = new GratitudeLogCreateRequest();
        req.setTitle("title");
        req.setContent("content");
        Long id = service.create(req);

        //when
        GratitudeLogEditRequest edit = service.findForEdit(id);

        //then
        assertThat(edit.getTitle()).isEqualTo("title");
        assertThat(edit.getContent()).isEqualTo("content");
    }

    @Test
    void findForEdit_存在しないIDは例外される() {
        //service.findForEdit(999L);
        assertThatThrownBy(() -> service.findForEdit(999L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("存在しないID: ");
    }
}
