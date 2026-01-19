package gratitude.gratitude_log.domain;

import lombok.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@Builder  @AllArgsConstructor @NoArgsConstructor
public class Account {

//    private Long id;
//
//    @Column(unique = true)
//    private String email;
//
//    @Column(unique = true)
//    private String nickname;
//
//    private String password;
//
//    // email 認証されたアカウント確認のため
//    private boolean emailVerified;
//
//    private String emailCheckToken;
//
//    private LocalDateTime joinedAt;
//
//    private String bio;
//
//    private String location; //var(255)
//
//    @Lob @Basic(fetch = FetchType.EAGER)
//    private String profileImage;
//
//    private boolean studyCreatedByEmail;
//
//    private boolean studyCreatedByWeb;
//
//    private boolean studyEnrollmentResultByEmail;
//
//    private boolean studyEnrollmentResultByWeb;
//
//    private boolean studyUpdatedByEmail;
//
//    private boolean studyUpdatedByWeb;
}
