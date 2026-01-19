package gratitude.gratitude_log.exception;

public class GratitudeLogNotFoundException extends RuntimeException {
    public GratitudeLogNotFoundException(Long id) {
        super("存在しないID：" + id);
    }
}
