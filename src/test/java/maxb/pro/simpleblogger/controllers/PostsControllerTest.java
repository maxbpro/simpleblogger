package maxb.pro.simpleblogger.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PostsController.class)
public class PostsControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostsController controller;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void allPosts() throws Exception {
    }

    @Test
    public void createPost() throws Exception {
    }

    @Test
    public void updatePost() throws Exception {
    }

    @Test
    public void deletePost() throws Exception {
    }

}