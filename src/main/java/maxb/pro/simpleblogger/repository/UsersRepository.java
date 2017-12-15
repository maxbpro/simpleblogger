package maxb.pro.simpleblogger.repository;

import maxb.pro.simpleblogger.models.Post;
import maxb.pro.simpleblogger.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository  extends MongoRepository<User, String> {
}
