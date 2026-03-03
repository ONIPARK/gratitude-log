package gratitude.account.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountReadResponse {
    private Long id;
    private String email;
    private String nickname;
    private boolean emailVerified;
    private LocalDateTime joinedAt;
}
