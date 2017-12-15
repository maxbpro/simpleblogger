package maxb.pro.simpleblogger.services;

import maxb.pro.simpleblogger.models.Post;

import java.util.List;

public interface PostsService {

    List<Post> getPosts();

    Post savePost(Post post);

    void deletePost(Post post);

    void deletePost(String id);

    Post findPost(String id);
}
