package io.iamogoe.techieblog.repository;

import io.iamogoe.techieblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
