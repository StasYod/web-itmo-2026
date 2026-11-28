package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class WritePostPage extends Page {
    private final UserService userService;

    public WritePostPage(UserService userService) {
        this.userService = userService;
    }

    @AnyRole({Role.Name.WRITER, Role.Name.ADMIN})
    @GetMapping("/writePost")
    public String writePostGet(Model model) {
        model.addAttribute("post", new Post());
        return "WritePostPage";
    }

    @AnyRole({Role.Name.WRITER, Role.Name.ADMIN})
    @PostMapping("/writePost")
    public String writePostPost(@Valid @ModelAttribute("post") Post post,
                                BindingResult bindingResult,
                                @RequestParam String tagsInput,
                                Model model,
                                HttpSession httpSession) {
        List<String> tagNames = new ArrayList<>();
        String tagsError = null;

        if (tagsInput != null && !tagsInput.trim().isEmpty()) {
            for (String name : tagsInput.trim().split("\\s+")) {
                if (!name.matches("[a-zA-Z]+")) {
                    tagsError = "Tags must contain only Latin letters";
                    break;
                }

                if (name.length() > 30) {
                    tagsError = "Tags must be no longer than 30 characters";
                    break;
                }

                if (tagNames.contains(name)) {
                    tagsError = "Tags must be unique";
                    break;
                }

                tagNames.add(name);
            }
        }

        if (bindingResult.hasErrors() || tagsError != null) {
            if (tagsError != null) {
                model.addAttribute("tagsError", tagsError);
            }
            return "WritePostPage";
        }

        userService.writePost(getUser(httpSession), post, tagNames);
        putMessage(httpSession, "You published new post");

        return "redirect:/myPosts";
    }
}
