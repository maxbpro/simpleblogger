package maxb.pro.simpleblogger.repository;

import com.mongodb.DBCollection;
import maxb.pro.simpleblogger.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


//@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataMongoTest
public class UsersRepositoryTest {

    private String collectionName;

    private User userToInsert;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Autowired
    private UsersRepository repository;


//    @Before
//    public void before() {
//        collectionName = "user";
//        userToInsert = new User();
//
//        User user1 = new User();
//        user1.setId("1");
//        user1.setUsername("user1");
//
//        User user2 = new User();
//        user2.setId("2");
//        user2.setUsername("user2");
//
//        User user3 = new User();
//        user3.setId("3");
//        user3.setUsername("user3");
//
//        repository.save(Arrays.asList(user1, user2, user3));
//    }
//
//    @After
//    public void after() {
//        mongoTemplate.dropCollection(collectionName);
//    }
//
//    @Test
//    public void checkMongoTemplate() {
//        assertNotNull(mongoTemplate);
//        //DBCollection createdCollection = mongoTemplate.createCollection(collectionName);
//        assertTrue(mongoTemplate.collectionExists(collectionName));
//    }
//
//    @Test
//    public void count() {
//        List<User> all = repository.findAll();
//        assertThat(all.size(), equalTo(3));
//    }
//
//    @Test
//    public void checkUsersRepository() {
//        assertNotNull(repository);
//        User savedUser = repository.save(userToInsert);
//        assertNotNull(repository.findOne(savedUser.getId()));
//    }
//
//    @Test
//    public void findById() {
//        User user = repository.findOne("2");
//        assertNotNull(user);
//        assertEquals(user.getUsername(), "user2");
//    }
//
//    @Test
//    public void testDelete() {
//
//        repository.delete("3");
//        assertThat(repository.findAll().size(), equalTo(2));
//    }
//
//    @Test
//    public void testDeleteUser() {
//
//        User user = repository.findOne("3");
//
//        repository.delete(user);
//        assertThat(repository.findAll().size(), equalTo(2));
//    }

//    @Test
//    public void checkDocumentAndQuery() {
//        mongoTemplate.save(userToInsert, collectionName);
//        Query query = new Query(new Criteria()
//                .andOperator(Criteria.where("level").regex(logRecToInsert.getLevel()),
//                        Criteria.where("message").regex(logRecToInsert.getMessage())));
//
//        User retrievedUser = mongoTemplate.findOne(query, User.class, collectionName);
//        assertNotNull(retrievedUser);
//    }





    @Test
    public void whenFindAllById() {
//        //given
//        Arrival arrival = new Arrival();
//        arrival.setCity("Yerevan");
//        entityManager.persist(arrival);
//        entityManager.flush();
//
//        //when
//        Arrival testArrival = arrivalRepository.findAllById(arrival.getId());
//
//        //then
//        assertThat(testArrival.getCity()).isEqualTo(arrival.getCity());
    }
}