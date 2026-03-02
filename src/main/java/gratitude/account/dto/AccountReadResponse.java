package gratitude.account.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AccountReadResponse {
    private Long id;
    private String email;
    private String nickname;
    private boolean emailVerified;
    private LocalDateTime joinedAt;
}
