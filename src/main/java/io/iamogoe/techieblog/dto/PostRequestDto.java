package io.iamogoe.techieblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "content is mandatory")
    private String content;
    @NotBlank(message = "Author is mandatory")
    private String author;
}
