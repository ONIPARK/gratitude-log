package gratitude.gratitude_log.exception;

// 404: 対象がない（存在しないフォームをidで検索・削除・修正）
public class GratitudeLogNotFoundException extends RuntimeException {
    public GratitudeLogNotFoundException(Long id) {
        super("存在しないID：" + id);
    }
}
