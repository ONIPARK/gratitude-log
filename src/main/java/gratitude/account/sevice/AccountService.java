package gratitude.account.sevice;

import gratitude.account.dto.AccountCreateForm;

public interface AccountService {
    Long create(AccountCreateForm form);
}
