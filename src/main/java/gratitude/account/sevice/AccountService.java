package gratitude.account.sevice;

import gratitude.account.dto.AccountCreateForm;
import gratitude.account.dto.AccountReadResponse;

public interface AccountService {
    Long create(AccountCreateForm form);
    AccountReadResponse findByEmail(String email);
}
