package gratitude.gratitude_log.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GratitudeLogListDto {
    private Long id;
    private String title;
    private LocalDateTime createAt;
}
