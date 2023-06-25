package io.iamogoe.techieblog.converter;

import io.iamogoe.techieblog.dto.PostRequestDto;
import io.iamogoe.techieblog.model.Post;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostBodyToEntity implements Converter<PostRequestDto, Post> {
    @Override
    public Post convert(PostRequestDto source) {
        return new Post()
                .setAuthor(source.getAuthor())
                .setTitle(source.getTitle())
                .setContent(source.getContent());
    }
}
