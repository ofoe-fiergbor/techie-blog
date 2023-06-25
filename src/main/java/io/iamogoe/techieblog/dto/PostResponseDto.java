package io.iamogoe.techieblog.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class PostResponseDto {
     int id;
     String title;
     String content;
     String author;
     LocalDateTime lastUpdated;
     LocalDateTime createdAt;
}
