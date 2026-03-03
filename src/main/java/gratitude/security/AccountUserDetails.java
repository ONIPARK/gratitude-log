package gratitude.security;

import gratitude.domain.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// AccountをSpringSecurityが理解できる形式に変換
@RequiredArgsConstructor
@Getter
public class AccountUserDetails implements UserDetails {

    // Account（ドメイン）をそのまま持ち、UserDetailsメソッドをAccountを基盤で実装し、Securityに提供。
    private final Account account;

    //権限(ROLE)を返却：Securityは接近制御（@PreAuthorize, hasRole, anMatchers など）から値を使用。
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // 検証時、入力したpasswordは比較する「保存されたパスワード（ハッシュ）」を提供。
    @Override
    public String getPassword() {
        return account.getPassword();
    }

    public String getNickname() {
        return account.getNickname();
    }

    //ユーザー識別子（userParameterをemailで渡す。）
    @Override
    public String getUsername() {
        return account.getEmail();
    }

    //アカウント満了可否
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // アカウントロッグ可否（例：パスワード何回失敗した場合、ロッグ）
    // falseの場合、ロッグ（現在ture固定）
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 資格証明（パスワード）満了可否
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //活性化可否（メールが承認されたアカウントのみログイン可）
    @Override
    public boolean isEnabled() {

        return true;
        // メール検証
        //return account.isEmailVerified();
    }
}
