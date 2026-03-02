package gratitude.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreateForm {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nickname;

    @NotBlank
    @Size(min = 8, max = 64)
    private String password;
}
