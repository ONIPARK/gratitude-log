package gratitude.gratitude_log.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GratitudeLogEditRequest {
    private Long id;
    private String title;
    private String content;
}
