package gratitude.account.sevice;

import gratitude.account.dto.AccountCreateForm;
import gratitude.account.dto.AccountReadResponse;
import gratitude.account.repository.AccountMapper;
import gratitude.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder; //BCrypt 保存

    public Long create(AccountCreateForm form) {
        if (accountMapper.existsByEmail(form.getEmail())) {
            throw new IllegalStateException("既に使用されているメールです。");
        }

        if (accountMapper.existsByNickname(form.getNickname())) {
            throw new IllegalStateException(("既に使用されているニックネームです。"));
        }

        Account account = Account.builder()
                .email(form.getEmail())
                .nickname(form.getNickname())
                .password(passwordEncoder.encode(form.getPassword()))
                .emailVerified(false)
                .joinedAt(LocalDateTime.now())
                .build();

        accountMapper.insert(account);
        return account.getId();
    }

    @Override
    public AccountReadResponse findByEmail(String email) {
        Account a = accountMapper.findByEmail(email);
        if (a == null) {
            throw new IllegalStateException("アドレスが見つかりませんでした。: " + email);
        }

        AccountReadResponse res = new AccountReadResponse();
        res.setId(a.getId());
        res.setEmail(a.getEmail());
        res.setNickname(a.getNickname());
        res.setEmailVerified(a.isEmailVerified());
        res.setJoinedAt(a.getJoinedAt());
        return res;
    }


}
