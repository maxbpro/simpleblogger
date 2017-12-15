package maxb.pro.simpleblogger.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "post")
public class Post {

    @Id
    private String id;
    private String title;
    private String permalink;
    private String content;
    private String tags;
    private String keywords;
    private String author;
    private LocalDateTime datePublished;
    private List<Comment> comments;
}
