package gratitude.account.controller;

import gratitude.account.dto.AccountCreateForm;
import gratitude.account.sevice.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/login")
    public String loginForm() {
        return "account/login";
    }

    //会員登録の画面
    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("form", new AccountCreateForm());
        return "account/sign-up";
    }

    //会員登録処理
    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid AccountCreateForm form,
                               BindingResult br,
                               Model model
    ) {
        if (br.hasErrors()) {
            model.addAttribute("form", form);
            return "account/sign-up";
        }

        try {
            accountService.create(form);
            return "redirect:/account/login?signup";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "account/sign-up";
        }
    }
}
