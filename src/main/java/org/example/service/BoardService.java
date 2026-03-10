package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.BoardRequestDto;
import org.example.dto.BoardResponseDto;
import org.example.entity.Board;
import org.example.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 추가
    public BoardResponseDto save(BoardRequestDto dto) {

        Board board = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        Board saved = boardRepository.save(board);

        return BoardResponseDto.fromEntity(board);
    }

    // 게시글 수정
    @Transactional
    public BoardResponseDto update(Long boardId, BoardRequestDto dto) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        board.update(dto.getTitle(), dto.getContent());

        return BoardResponseDto.fromEntity(board);
    }

//    // 게시글 전체 조회(페이징 처리)
//    public Page<BoardResponseDto> findAll(Pageable pageable) {
//        return boardRepository.findAll(pageable)
//                .map(BoardResponseDto::fromEntity);
//    }

    // 게시글 전체 조회
    public Page<BoardResponseDto> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable)
                .map(BoardResponseDto::fromEntity);
    }

    // 게시글 검색(제목)
    public Page<BoardResponseDto> search(String keyword, Pageable pageable) {
        System.out.println("검색어 : " + keyword);

        return boardRepository.findByTitleContainingIgnoreCase(keyword, pageable)
                .map(BoardResponseDto::fromEntity);
    }

    // 게시글 순번에 따른 조회(1건)
    public BoardResponseDto findById(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        board.increaseViewCount();

        return BoardResponseDto.fromEntity(board);
    }


    // 게시글 삭제
    public void delete(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
