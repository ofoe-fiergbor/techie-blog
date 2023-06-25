package io.iamogoe.techieblog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PostUpdateRequestDto {
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Content is mandatory")
    private String content;
}
