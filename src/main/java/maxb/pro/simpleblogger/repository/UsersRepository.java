package maxb.pro.simpleblogger.repository;

import maxb.pro.simpleblogger.models.Post;
import maxb.pro.simpleblogger.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsersRepository  extends MongoRepository<User, String> {

    User findByUsername (String username);
}
