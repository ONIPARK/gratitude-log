package gratitude.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
//@EqualsAndHashCode(of = "id")
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class Account {

    private Long id;
    private String email;
    private String nickname;
    private String password;

    // email 認証されたアカウント確認のため
    private boolean emailVerified;
    private String emailCheckToken;
    private LocalDateTime joinedAt;

    //private String bio;

    // 住所、郵便番号
    //private String location; //var(255)

    //@Lob @Basic(fetch = FetchType.EAGER)
    //private String profileImage;

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
