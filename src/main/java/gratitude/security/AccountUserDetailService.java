package gratitude.security;

import gratitude.account.repository.AccountMapper;
import gratitude.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountUserDetailService implements UserDetailsService {

    private final AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountMapper.findByEmail(email);
        System.out.println("login email = " + email);
        System.out.println("DB password = " + (account == null ? null : account.getPassword()));

        if (account == null) {
            throw new UsernameNotFoundException("存在しないID：" + email);
        }

        return new AccountUserDetails(account);
    }
}
