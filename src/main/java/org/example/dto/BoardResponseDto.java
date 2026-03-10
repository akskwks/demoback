package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.entity.Board;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardResponseDto {

    private Long boardId;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int viewCount;

    public static BoardResponseDto fromEntity(Board board) {
        return BoardResponseDto.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .viewCount(board.getViewCount())
                .build();
    }
}
