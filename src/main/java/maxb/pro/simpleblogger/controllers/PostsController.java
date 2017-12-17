package maxb.pro.simpleblogger.controllers;

import maxb.pro.simpleblogger.models.Post;
import maxb.pro.simpleblogger.models.User;
import maxb.pro.simpleblogger.repository.PostsRepository;
import maxb.pro.simpleblogger.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @PostConstruct
    private void initPosts(){

        postsRepository.deleteAll();

        for(int i = 0; i < 10; i++){
            Post post = new Post();
            post.setTitle("post" + i);
            post.setTags("tag 1, tg 2");
            post.setKeywords("keyword1, keyword2");
            post.setContent("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum ");
            post.setAuthor("Maxb");
            post.setPermalink("permalink1");
            postsService.savePost(post);
        }


    }


    @GetMapping("/posts")
    public List<Post> allPosts() {
        return postsService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable String id) {
        return postsService.findPost(id);
    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postsService.savePost(post);
    }

//    @PutMapping("/posts/")
//    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
//
//        Post oldPost = postsService.findPost(id);
//        if (oldPost == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        oldPost.setContent (post.getContent());
//        oldPost.setKeywords(post.getKeywords());
//        oldPost.setTags(post.getTags());
//        oldPost.setTitle(post.getTitle());
//        Post updatedPost = postsService.savePost(oldPost);
//        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
//    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") String id) {
        postsService.deletePost(id);
        return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
    }
}
