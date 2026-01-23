package gratitude.gratitude_log.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GratitudeLogSearchCond {
    private String keyword; // 検索キーワード
    private String sort; // 整列
}
