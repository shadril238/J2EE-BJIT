package bjit.ursa.bookservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyBookResponse {
    private Long bookId;
    private String bookName;
    private Integer quantity;
    private Double totalPrice;
}