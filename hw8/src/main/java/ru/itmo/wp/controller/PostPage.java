package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.CommentService;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;

@Controller
public class PostPage extends Page {
    private final PostService postService;
    private final CommentService commentService;

    public PostPage(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @Guest
    @GetMapping("/post/{id}")
    public String post(@PathVariable long id, Model model) {
        Post post = postService.findById(id);

        if (post == null) {
            return "redirect:/";
        }

        model.addAttribute("post", post);
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String addComment(
            @PathVariable long id,
            @RequestParam String text,
            HttpSession httpSession) {
        User user = getUser(httpSession);
        Post post = postService.findById(id);

        if (user == null || post == null) {
            return "redirect:/";
        }

        if (text == null || text.trim().isEmpty()) {
            putMessage(httpSession, "Comment must not be empty");
            return "redirect:/post/" + id;
        }

        commentService.addComment(user, post, text.trim());
        putMessage(httpSession, "Comment successfully added");

        return "redirect:/post/" + id;
    }
}
