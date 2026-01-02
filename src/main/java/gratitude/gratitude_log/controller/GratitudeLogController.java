package gratitude.gratitude_log.controller;

import gratitude.gratitude_log.dto.GratitudeLogCreateRequest;
import gratitude.gratitude_log.dto.GratitudeLogEditRequest;
import gratitude.gratitude_log.service.GratitudeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gratitude")
public class GratitudeLogController {

    private final GratitudeLogService gratitudeLogService;

    // CREATE
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
        ra.addFlashAttribute("flashMessage", "保存されました。");

        return "redirect:/gratitude/list";
    }

    // READ
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("items", gratitudeLogService.findAllForList());
        return "gratitude/list";
    }

    // UPDATE
    // 1) 修正画面
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("req", gratitudeLogService.findForEdit(id));
        model.addAttribute("id", id);
        return "gratitude/edit";
    }
    // 2) 修正処理
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       @ModelAttribute("req")GratitudeLogEditRequest req,
                       RedirectAttributes ra) {
        gratitudeLogService.update(id, req);
        ra.addFlashAttribute("flashMessage", "修正しました。");
        return "redirect:/gratitude/list";
    }

    //DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra) {
        gratitudeLogService.delete(id);
        ra.addFlashAttribute("flashMessage", "削除しました。");
        return "redirect:/gratitude/list";
    }
}
