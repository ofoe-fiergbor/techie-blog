package io.iamogoe.techieblog.converter;

import io.iamogoe.techieblog.dto.PostResponseDto;
import io.iamogoe.techieblog.model.Post;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToResponse implements Converter<Post, PostResponseDto> {
    @Override
    public PostResponseDto convert(Post source) {
        return PostResponseDto.builder()
                .id(source.getId())
                .author(source.getAuthor())
                .title(source.getTitle())
                .content(source.getContent())
                .lastUpdated(source.getUpdatedAt())
                .createdAt(source.getUpdatedAt())
                .build();
    }
}
