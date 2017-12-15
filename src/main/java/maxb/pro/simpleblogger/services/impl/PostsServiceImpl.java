package maxb.pro.simpleblogger.services.impl;

import maxb.pro.simpleblogger.models.Post;
import maxb.pro.simpleblogger.repository.PostsRepository;
import maxb.pro.simpleblogger.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsRepository postsRepository;


    @Override
    public List<Post> getPosts() {
        return postsRepository.findAll();
    }

    @Override
    public Post savePost(Post post) {
        return postsRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {
        postsRepository.delete(post);
    }

    @Override
    public void deletePost(String id) {
        postsRepository.delete(id);
    }

    @Override
    public Post findPost(String id) {
        return postsRepository.findOne(id);
    }
}
