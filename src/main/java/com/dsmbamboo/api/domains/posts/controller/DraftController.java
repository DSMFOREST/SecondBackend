package com.dsmbamboo.api.domains.posts.controller;

import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.dto.DraftResponse;
import com.dsmbamboo.api.domains.posts.exception.ArticleNotFoundException;
import com.dsmbamboo.api.domains.posts.service.DraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/drafts")
@RequiredArgsConstructor
public class DraftController {

    private final DraftService draftService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public Page<DraftResponse> findAll(Pageable pageable) {
        return draftService.findAll(pageable)
                .map(DraftResponse::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DraftResponse create(@RequestHeader("X-Document-Key") String documentKey, @RequestBody @Valid CreateArticleRequest request) {
        return new DraftResponse(draftService.create(documentKey, request));
    }

    @GetMapping("/{draftId}")
    @Secured("ROLE_ADMIN")
    public DraftResponse findByDraftId(@PathVariable @Valid Long draftId) {
        return draftService.findByArticleId(draftId)
                .map(DraftResponse::new)
                .orElseThrow(ArticleNotFoundException::new);
    }

    @PatchMapping("/{draftId}/approve")
    @Secured("ROLE_ADMIN")
    public DraftResponse approve(@PathVariable @Valid Long draftId) {
        return draftService.approve(draftId)
                .map(DraftResponse::new)
                .orElseThrow(ArticleNotFoundException::new);
    }

    @PatchMapping("/{draftId}/disapprove")
    @Secured("ROLE_ADMIN")
    public DraftResponse disapprove(@PathVariable @Valid Long draftId) {
        return draftService.disapprove(draftId)
                .map(DraftResponse::new)
                .orElseThrow(ArticleNotFoundException::new);
    }

}
