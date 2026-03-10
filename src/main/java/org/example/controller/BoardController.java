package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.BoardRequestDto;
import org.example.dto.BoardResponseDto;
import org.example.entity.Board;
import org.example.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BoardController {

    @Autowired
    private final BoardService boardService;

    @PostMapping
    public BoardResponseDto create(@RequestBody BoardRequestDto dto) {
        return boardService.save(dto);
    }

//    @GetMapping
//    public Page<BoardResponseDto> list(Pageable pageable) {
//        return boardService.findAll(pageable);
//    }

    @GetMapping
    public Page<BoardResponseDto> list(@RequestParam(required = false) String keyword,
                                       @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC)
                                       Pageable pageable) {
        if (keyword == null || keyword.isEmpty()) {
            return boardService.findAll(pageable);
        }

        return boardService.search(keyword, pageable);
    }

    @GetMapping("/{boardId}")
    public BoardResponseDto detail(@PathVariable Long boardId) {
        return boardService.findById(boardId);
    }

    @PutMapping("/{boardId}")
    public BoardResponseDto update(@PathVariable Long boardId, @RequestBody BoardRequestDto dto) {
        return boardService.update(boardId, dto);
    }

    @DeleteMapping("/{boardId}")
    public void delete(@PathVariable Long boardId) {
        boardService.delete(boardId);
    }


}
