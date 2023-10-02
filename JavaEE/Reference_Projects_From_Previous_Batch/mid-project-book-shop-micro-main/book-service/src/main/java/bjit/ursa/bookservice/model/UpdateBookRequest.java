package bjit.ursa.bookservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBookRequest {
    @Valid

    @NotNull(message = "Book id is required")
    private Long bookId;
    @NotEmpty(message = "Book name is required")
    private String bookName;
    @NotEmpty(message = "Author name is required")
    private String authorName;
    @NotEmpty(message = "Genre is required")
    private String genre;
    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price should not be less than 1$")
    private Double price;
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity should not be less than 1")
    private Integer quantity;
}