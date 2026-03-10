package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Board;
import org.example.entity.Comment;
import org.example.repository.BoardRepository;
import org.example.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Comment save(Long id, String content, String writer) {

        Board board = boardRepository.findById(id).orElseThrow();

        Comment comment = Comment.builder()
                .content(content)
                .writer(writer)
                .board(board)
                .build();

        return commentRepository.save(comment);
    }

    public List<Comment> findByBoard(Long id) {
        return commentRepository.findByBoardBoardIdOrderByCommentIdDesc(id);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
