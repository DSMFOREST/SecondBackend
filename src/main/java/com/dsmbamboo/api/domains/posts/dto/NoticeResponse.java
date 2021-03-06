package com.dsmbamboo.api.domains.posts.dto;

import com.dsmbamboo.api.domains.images.dto.ImageResponse;
import com.dsmbamboo.api.domains.posts.model.Article;
import com.dsmbamboo.api.domains.posts.model.Category;
import com.dsmbamboo.api.utils.TimeCalculator;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class NoticeResponse {

    private Long id;
    private String title;
    private String content;
    private String facebookLink;
    private List<String> categories;
    private List<String> images;
    private LocalDateTime createdAt;
    private String recentCreatedAt;
    private LocalDateTime approvedAt;
    private String recentApprovedAt;

    public NoticeResponse(Article article) {
        this.id = article.getPublishedId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.facebookLink = article.getFacebookLink();
        this.categories = article.getCategories()
                .stream().map(Category::getName).collect(Collectors.toList());
        this.images = article.getImages()
                .stream().map(image -> ImageResponse.IMAGE_URL_PREFIX.concat(image.getUrl())).collect(Collectors.toList());
        this.createdAt = article.getCreatedAt();
        this.recentCreatedAt = TimeCalculator.toRecentKorean(article.getCreatedAt());
        this.approvedAt = article.getModifiedAt();
        this.recentApprovedAt = TimeCalculator.toRecentKorean(article.getModifiedAt());
    }

}
