package gratitude.gratitude_log.service;

import gratitude.domain.GratitudeLog;
import gratitude.gratitude_log.dto.GratitudeLogCreateRequest;
import gratitude.gratitude_log.dto.GratitudeLogEditRequest;
import gratitude.gratitude_log.dto.GratitudeLogListDto;
import gratitude.gratitude_log.dto.GratitudeLogSearchCond;
import gratitude.gratitude_log.exception.GratitudeLogNotFoundException;
import gratitude.gratitude_log.repository.GratitudeLogMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GratitudeLogServiceTest {

    @Mock GratitudeLogMapper mapper;

    GratitudeLogService service;

    @BeforeEach
    void setUp() {
        service = new GratitudeLogServiceImpl(mapper);
    }

    // 1) create
    @Test
    void crate_inserts_and_return_id() {
        // given
        GratitudeLogCreateRequest req = new GratitudeLogCreateRequest();
        req.setTitle("タイトル");
        req.setContent("内容");

        //insertが呼び出し際、id自動生成（想定）
        doAnswer(inv -> {
            GratitudeLog arg = inv.getArgument(0);
            arg.setId(1L);
            return 1;
        }).when(mapper).insert(any(GratitudeLog.class));

        // when
        Long id = service.create(req);

        // then
        assertThat(id).isEqualTo(1L);
        verify(mapper, times(1)).insert(any(GratitudeLog.class));
    }

    // 2) list 照会
    @Test
    void findAllForList_maps_domain_to_dto() {
        // given
        LocalDateTime now = LocalDateTime.now();
        GratitudeLog g1 = GratitudeLog.builder().id(1L).title("title1").content("content1").createdAt(now).build();
        GratitudeLog g2 = GratitudeLog.builder().id(2L).title("title2").content("content2").createdAt(now).build();

        when(mapper.selectAll()).thenReturn(List.of(g1, g2));

        // when
        List<GratitudeLogListDto> result = service.findAllForList();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(0).getTitle()).isEqualTo("title1");
        assertThat(result.get(0).getContent()).isEqualTo("content1");
        assertThat(result.get(0).getCreatedAt()).isEqualTo(now);

        verify(mapper, times(1)).selectAll();
    }

    // 3. update
    // 3-1 id検索(findForEdit)
    @Test
    void findForEdit_throws_when_not_found() {
        //service.findForEdit(999L);
        // given
        when(mapper.findForEdit(999L)).thenReturn(null);

        // when & then
        assertThatThrownBy(() -> service.findForEdit(999L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("存在しないID");

        verify(mapper, times(1)).findForEdit(999L);
    }

    // 3-2 findForEdit 機能確認
    @Test
    void findForEdit_returns_request_when_found() {
        // given
        GratitudeLogEditRequest edit = new GratitudeLogEditRequest();
        edit.setTitle("editTitle");
        edit.setContent("editContent");

        when(mapper.findForEdit(1L)).thenReturn(edit);

        // when
        GratitudeLogEditRequest result = service.findForEdit(1L);

        // then
        assertThat(result.getTitle()).isEqualTo("editTitle");
        assertThat(result.getContent()).isEqualTo("editContent");
        verify(mapper, times(1)).findForEdit(1L);
    }

    // 3-3 失敗
    @Test
    void update_throws_when_updated_0() {
        // given
        GratitudeLogEditRequest req = new GratitudeLogEditRequest();
        req.setTitle("title");
        req.setContent("content");

        when(mapper.update(req)).thenReturn(0);

        // when & then
        assertThatThrownBy(() -> service.update(req))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("存在しないID");

        verify(mapper, times(1)).update(req);
    }

    // 3-4 成功
    @Test
    void update_throws_when_updated_1() {
        // given
        GratitudeLogEditRequest req = new GratitudeLogEditRequest();
        req.setId(1L);
        req.setTitle("title");
        req.setContent("content");

        when(mapper.update(req)).thenReturn(1);

        // when
        service.update(req);

        // then
        verify(mapper, times(1)).update(req);
    }

    // 4. delete
    @Test
    void delete_throws_notFound_when_deleted0() {
        // given
        when(mapper.delete(1L)).thenReturn(0);

        // when & then
        assertThatThrownBy(() -> service.delete(1L))
                .isInstanceOf(GratitudeLogNotFoundException.class);

        verify(mapper, times(1)).delete(1L);
    }

    @Test
    void delete_throws_notFound_when_deleted1() {
        //given
        when(mapper.delete(1L)).thenReturn(1);

        //when
        service.delete(1L);

        //then
        verify(mapper, times(1)).delete(1L);
    }

    // 5. findAll
    @Test
    void findAll_trims_keyword_and_passes_to_mapper() {
        // given
        GratitudeLogSearchCond cond = new GratitudeLogSearchCond();
        cond.setKeyword("    感謝    ");

        when(mapper.findAll(any(GratitudeLogSearchCond.class))).thenReturn(List.of());

        // when
        service.findAll(cond);

        // then: mapperに渡されたkeywordが 「感謝」 なのか確認
        ArgumentCaptor<GratitudeLogSearchCond> captor = ArgumentCaptor.forClass(GratitudeLogSearchCond.class);
        verify(mapper).findAll(captor.capture());
        assertThat(captor.getValue().getKeyword()).isEqualTo("感謝");
    }
}
