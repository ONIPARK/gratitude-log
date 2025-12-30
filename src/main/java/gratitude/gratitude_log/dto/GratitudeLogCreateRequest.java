package gratitude.gratitude_log.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GratitudeLogCreateRequest {

    private String title;
    private String content;

}
