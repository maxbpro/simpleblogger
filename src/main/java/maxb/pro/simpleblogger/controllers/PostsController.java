package maxb.pro.simpleblogger.controllers;

import maxb.pro.simpleblogger.models.Post;
import maxb.pro.simpleblogger.models.User;
import maxb.pro.simpleblogger.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostsController {

    @Autowired
    private PostsService postsService;


    @GetMapping("/posts")
    public List<Post> allPosts() {
        return postsService.getPosts();
    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postsService.savePost(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") String id, @RequestBody Post post) {

        Post oldPost = postsService.findPost(id);
        if (oldPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oldPost.setContent (post.getContent());
        oldPost.setKeywords(post.getKeywords());
        oldPost.setTags(post.getTags());
        oldPost.setTitle(post.getTitle());
        Post updatedPost = postsService.savePost(oldPost);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") String id) {
        postsService.deletePost(id);
        return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
    }
}
