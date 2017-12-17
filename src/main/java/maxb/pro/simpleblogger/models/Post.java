package maxb.pro.simpleblogger.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private LocalDateTime datePublished = LocalDateTime.now();

    private List<Comment> comments = new ArrayList<>();


}
