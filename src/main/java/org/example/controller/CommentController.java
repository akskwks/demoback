package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Comment;
import org.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@CrossOrigin
public class CommentController {

    @Autowired
    private final CommentService commentService;

    @PostMapping("/{boardId}")
    public Comment create(
            @PathVariable Long id,
            @RequestBody Comment request) {

        return commentService.save(
                id,
                request.getContent(),
                request.getWriter()
        );
    }

    @GetMapping("/{id}")
    public List<Comment> get(@PathVariable Long id) {
        return commentService.findByBoard(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        commentService.delete(id);
    }

}
