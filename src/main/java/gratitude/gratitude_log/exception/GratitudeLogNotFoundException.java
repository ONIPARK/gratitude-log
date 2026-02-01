package gratitude.gratitude_log.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 404: 対象がない（存在しないフォームをidで検索・削除・修正）
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GratitudeLogNotFoundException extends RuntimeException {
    public GratitudeLogNotFoundException(Long id) {
        super("存在しないID：" + id);
    }
}
