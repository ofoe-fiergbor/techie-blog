package io.iamogoe.techieblog.controller;

import io.iamogoe.techieblog.dto.PostRequestDto;
import io.iamogoe.techieblog.dto.PostResponseDto;
import io.iamogoe.techieblog.dto.PostUpdateRequestDto;
import io.iamogoe.techieblog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {
    private final IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> createNewPost(@Valid @RequestBody PostRequestDto body) {
        return new ResponseEntity<>(postService.createPost(body), CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(
            @PathVariable int postId) {
        return new ResponseEntity<>(postService.getPostById(postId), OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable int postId,
            @Valid @RequestBody PostUpdateRequestDto body) {
        return new ResponseEntity<>(postService.updatePost(postId, body), OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable(name = "postId") int postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(OK);
    }
}
