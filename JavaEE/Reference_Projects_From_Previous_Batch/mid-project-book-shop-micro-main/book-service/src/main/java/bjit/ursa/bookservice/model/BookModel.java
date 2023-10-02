package bjit.ursa.bookservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookModel {

    private Long bookId;
    private String bookName;
    private String authorName;
    private String genre;
    private Double price;
    private Integer quantity;
}