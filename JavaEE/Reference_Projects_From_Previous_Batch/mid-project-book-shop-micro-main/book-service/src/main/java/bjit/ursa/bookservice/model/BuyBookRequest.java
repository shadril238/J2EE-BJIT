package bjit.ursa.bookservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyBookRequest {
    @NotNull(message = "Book id is required")
    private Long id;
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity should not be less than 1")
    private Integer quantity;
}