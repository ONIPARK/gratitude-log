package gratitude.gratitude_log.controller;

import gratitude.gratitude_log.dto.GratitudeLogCreateRequest;
import gratitude.gratitude_log.service.GratitudeLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/gratitude")
public class GratitudeLogController {

    private final GratitudeLogService gratitudeLogService;

    public GratitudeLogController(GratitudeLogService service) {
        this.gratitudeLogService = service;
    }

    // 登録
    @GetMapping("/new")
    public String crateForm(Model model) {
        model.addAttribute("req", new GratitudeLogCreateRequest());
        return "gratitude/create";
    }

    @PostMapping
    public String create(@ModelAttribute("req") GratitudeLogCreateRequest req, RedirectAttributes ra) {
        Long id = gratitudeLogService.create(req);

        // 保存完了メッセージ + フォーム初期化
//        model.addAttribute("saveId", id);
//        model.addAttribute("req", new GratitudeLogCreateRequest());

        // redirect後、1回だけ転送される
        ra.addFlashAttribute("saveId", id);

        return "redirect:/gratitude/list";
    }

    // リスト
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("items", gratitudeLogService.findAllForList());
        return "gratitude/list";
    }
}
