package gratitude.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Account {

    private Long id;
    private String email;
    private String nickname;
    private String password;

    // email 認証されたアカウント確認のため
    private boolean emailVerified;
    private String emailCheckToken;
    private LocalDateTime joinedAt;

}
