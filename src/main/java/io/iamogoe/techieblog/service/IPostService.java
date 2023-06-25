package io.iamogoe.techieblog.service;

import io.iamogoe.techieblog.dto.PostResponseDto;
import io.iamogoe.techieblog.dto.PostRequestDto;
import io.iamogoe.techieblog.dto.PostUpdateRequestDto;

import java.util.List;

public interface IPostService {
    PostResponseDto createPost(PostRequestDto body);
    PostResponseDto getPostById(int postId);
    PostResponseDto updatePost(int postId, PostUpdateRequestDto body);
    void deletePost(int postId);
    List<PostResponseDto> getAllPosts();
}
