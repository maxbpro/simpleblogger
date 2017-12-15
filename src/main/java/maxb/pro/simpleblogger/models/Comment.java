package maxb.pro.simpleblogger.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {

    private String content;
    private String  author;
    private LocalDateTime datePublished;
}
