package io.iamogoe.techieblog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.iamogoe.techieblog.dto.PostRequestDto;
import io.iamogoe.techieblog.dto.PostResponseDto;
import io.iamogoe.techieblog.dto.PostUpdateRequestDto;
import io.iamogoe.techieblog.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PostController.class})
@WebMvcTest(controllers = PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PostService postService;
    @Autowired
    private ObjectMapper mapper;
    private final PostRequestDto body = new PostRequestDto("Dummy Title", "Dummy Content", "Author 1");
    private final PostResponseDto response = PostResponseDto.builder()
            .id(1)
            .title("Dummy Title")
            .content("Dummy Content")
            .author("Author 1")
            .build();
    @Test
    void shouldCreateNewPost() throws Exception {
        when(postService.createPost(body)).thenReturn(response);
        mockMvc.perform(post("/api/v1/posts")
                .content(mapper.writeValueAsString(body))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString())
                        .isEqualTo(mapper.writeValueAsString(response)));
    }

    @Test
    void shouldReturnBadRequest_whenRequestBodyIsWrong () throws Exception {
        PostRequestDto requestDto = new PostRequestDto();
        requestDto.setAuthor("Author");
        mockMvc.perform(post("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetAllPosts() throws Exception {
        List<PostResponseDto> responseList = List.of(response);
        when(postService.getAllPosts()).thenReturn(responseList);
        mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString())
                        .isEqualTo(mapper.writeValueAsString(responseList)));
    }

    @Test
    void shouldGetPostById() throws Exception {
        when(postService.getPostById(1)).thenReturn(response);
        mockMvc.perform(get("/api/v1/posts/1"))
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString())
                        .isEqualTo(mapper.writeValueAsString(response)));
    }

    @Test
    void shouldUpdatePost() throws Exception {
        PostResponseDto updated = PostResponseDto.builder()
                .id(1)
                .title("Updated Title")
                .content("Updated Content")
                .build();
        PostUpdateRequestDto updateRequestDto = new PostUpdateRequestDto();
        updateRequestDto.setContent("Updated Content");
        updateRequestDto.setTitle("Updated Title");
        when(postService.updatePost(1, updateRequestDto)).thenReturn(updated);
        mockMvc.perform(put("/api/v1/posts/1")
                        .content(mapper.writeValueAsString(updateRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString())
                        .isEqualTo(mapper.writeValueAsString(updated)));
    }
}