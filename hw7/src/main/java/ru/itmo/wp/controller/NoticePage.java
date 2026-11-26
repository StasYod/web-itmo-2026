package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Notice;
import ru.itmo.wp.form.NoticeForm;
import ru.itmo.wp.form.validator.UserCredentialsRegisterValidator;
import ru.itmo.wp.service.NoticeService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NoticePage extends Page {
    private final NoticeService noticeService;

    public NoticePage(final NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice/add")
    public String noticeGet(Model model) {
        model.addAttribute("noticeForm", new NoticeForm());
        return "NoticePage";
    }

    @PostMapping("/notice/add")
    public String noticePost(
            @Valid @ModelAttribute("noticeForm") NoticeForm noticeForm,
            BindingResult bindingResult,
            HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "NoticePage";
        }

        Notice notice = new Notice();
        notice.setContent(noticeForm.getContent());
        noticeService.save(notice);

        setMessage(httpSession, "Notice added!");

        return "redirect:/";
    }
}
