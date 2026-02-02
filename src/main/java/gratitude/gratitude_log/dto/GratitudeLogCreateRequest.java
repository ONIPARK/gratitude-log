package gratitude.gratitude_log.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GratitudeLogCreateRequest {

    @NotBlank(message = "タイトルは必須です。")
    @Size(max = 100, message = "タイトルは１００文字以内で入力してください。")
    private String title;

    @NotBlank(message = "内容は必須です。")
    @Size(max = 2000, message = "内容は2000文字以内で入力してください。")
    private String content;

}
