package gratitude.account.service;

import gratitude.account.dto.AccountCreateForm;
import gratitude.account.repository.AccountMapper;
import gratitude.account.sevice.AccountService;
import gratitude.account.sevice.AccountServiceImpl;
import gratitude.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    AccountMapper mapper;
    @Mock
    PasswordEncoder encoder;

    //実装クラスに注入
    @InjectMocks
    AccountServiceImpl service;

    @Test
    void create_insert_and_return_id() {
        //given
        AccountCreateForm form = new AccountCreateForm();
        form.setEmail("user@gmail.com");
        form.setPassword("0000");
        form.setNickname("onion");

        //重複チェック
        when(mapper.existsByEmail("user@gmail.com")).thenReturn(false);
        when(mapper.existsByNickname("onion")).thenReturn(false);

        //パスワードインコーディング
        when(encoder.encode("0000")).thenReturn("ENC(0000)");

        //insert
        doAnswer(inv -> {
            Account a = inv.getArgument(0);
            a.setId(1L);
            return 1;
        }).when(mapper).insert(any(Account.class));

        //when
        Long id = service.create(form);

        //then
        assertThat(id).isEqualTo(1L);
        verify(mapper, times(1)).insert(any(Account.class));
    }


}
