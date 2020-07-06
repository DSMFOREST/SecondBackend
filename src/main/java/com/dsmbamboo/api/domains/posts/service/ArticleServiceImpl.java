package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.images.model.ImageRepository;
import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.model.Article;
import com.dsmbamboo.api.domains.posts.model.ArticleRepository;
import com.dsmbamboo.api.domains.posts.model.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    @Override
    public Page<Article> findAllByCategories_Name(String categoryName, Pageable pageable) {
        return articleRepository.findAllByCategories_Name(categoryName, pageable);
    }

    @Override
    public Optional<Article> findByCategories_NameAndPublishedId(String categoryName, Long publishedId) {
        return articleRepository.findAllByCategories_NameAndPublishedId(categoryName, publishedId);
    }

    @Override
    public Article create(CreateArticleRequest request, Long publishedId) {
        return articleRepository.save(Article.builder()
                .id(0L)
                .title(request.getTitle())
                .content(request.getContent())
                .categories(categoryRepository.findAllById(request.getCategories()))
                .images(imageRepository.findAllById(request.getImages()))
                .publishedId(publishedId)
                .approver(null)
                .isActive(true)
                .facebookLink(null)
                .build());
    }

}
