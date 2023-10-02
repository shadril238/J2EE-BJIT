package bjit.ursa.bookservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryModel {
    private Long bookId;
    private double bookPrice;
    private Integer bookQuantity;
}