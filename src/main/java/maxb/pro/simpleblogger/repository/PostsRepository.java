package maxb.pro.simpleblogger.repository;

import maxb.pro.simpleblogger.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostsRepository extends MongoRepository<Post, Long> {
}
