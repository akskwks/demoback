package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Comment;
import org.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardId}/comments")
@RequiredArgsConstructor
@CrossOrigin
public class CommentController {

    @Autowired
    private final CommentService commentService;

    @PostMapping
    public Comment create(
            @PathVariable Long boardId,
            @RequestBody Comment request) {

        return commentService.save(
                boardId,
                request.getContent(),
                request.getWriter()
        );
    }

    @GetMapping
    public List<Comment> get(@PathVariable Long boardId) {
        return commentService.findByBoard(boardId);
    }

    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
    }

}
