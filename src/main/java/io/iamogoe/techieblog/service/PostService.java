package io.iamogoe.techieblog.service;

import io.iamogoe.techieblog.converter.EntityToResponse;
import io.iamogoe.techieblog.converter.PostBodyToEntity;
import io.iamogoe.techieblog.dto.PostRequestDto;
import io.iamogoe.techieblog.dto.PostResponseDto;
import io.iamogoe.techieblog.dto.PostUpdateRequestDto;
import io.iamogoe.techieblog.model.Post;
import io.iamogoe.techieblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final EntityToResponse entityToResponse;
    private final PostBodyToEntity postBodyToEntity;

    @Autowired
    public PostService(PostRepository postRepository,
                       EntityToResponse entityToResponse,
                       PostBodyToEntity postBodyToEntity) {
        this.postRepository = postRepository;
        this.entityToResponse = entityToResponse;
        this.postBodyToEntity = postBodyToEntity;
    }

    @Override
    public PostResponseDto createPost(PostRequestDto body) {
        var post = postBodyToEntity.convert(body);
        var savedPost = postRepository.save(Objects.requireNonNull(post));
        return entityToResponse.convert(savedPost);
    }

    @Override
    public PostResponseDto getPostById(int postId) {
        var foundPost = fetchPostById(postId);
        return entityToResponse.convert(foundPost);
    }

    @Override
    public PostResponseDto updatePost(int postId, PostUpdateRequestDto body) {
        var foundPost = fetchPostById(postId);
        foundPost.setContent(body.getContent());
        foundPost.setTitle(body.getTitle());
        postRepository.save(foundPost);
        return entityToResponse.convert(foundPost);
    }

    @Override
    public void deletePost(int postId) {
        var foundPost = fetchPostById(postId);
        postRepository.delete(foundPost);
    }

    @Override
    public List<PostResponseDto> getAllPosts() {
        var allPosts = postRepository.findAll();
        return allPosts.stream().map(entityToResponse::convert).toList();
    }
    private Post fetchPostById(int postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id: %s not found".formatted(postId)));
    }
}
