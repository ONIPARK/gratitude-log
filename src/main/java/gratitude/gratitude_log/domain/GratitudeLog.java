package gratitude.gratitude_log.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GratitudeLog {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
