package bjit.ursa.inventoryservice.service;

import bjit.ursa.inventoryservice.entity.InventoryEntity;
import bjit.ursa.inventoryservice.model.APIResponse;
import bjit.ursa.inventoryservice.model.BuyBookRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryService {
    ResponseEntity<APIResponse<?>> updateBooks(Long bookId, InventoryEntity inventoryEntity);

    ResponseEntity<APIResponse<?>> fetchId(Long bookId);

    ResponseEntity<APIResponse<?>> fetchAllData(List<Long> ids);

    ResponseEntity<APIResponse<?>> deleteInventoryById(Long bookId);

    ResponseEntity<APIResponse<?>> deductQuantity(BuyBookRequest buyBookRequest);
}