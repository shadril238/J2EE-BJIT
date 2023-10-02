package bjit.ursa.inventoryservice.service.Implementation;

import bjit.ursa.inventoryservice.entity.InventoryEntity;
import bjit.ursa.inventoryservice.exception.InventoryServiceException;
import bjit.ursa.inventoryservice.model.APIResponse;
import bjit.ursa.inventoryservice.model.BuyBookRequest;
import bjit.ursa.inventoryservice.repository.InventoryRepository;
import bjit.ursa.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImp implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional
    @Override
    public ResponseEntity<APIResponse<?>> updateBooks(Long bookId, InventoryEntity request) {
        try {
            Optional<InventoryEntity> updateInventory = inventoryRepository.findById(bookId);
            if (updateInventory.isPresent()) {
                InventoryEntity inventoryEntity = updateInventory.get();
                // Update the book entity with the new values from the request model
                inventoryEntity.setBookPrice(request.getBookPrice());
                inventoryEntity.setBookQuantity(request.getBookQuantity());
                // Save the updated book entity
                InventoryEntity updatedBook = inventoryRepository.save(inventoryEntity);
                APIResponse<InventoryEntity> apiResponse = new APIResponse<InventoryEntity>(updatedBook, null);
                return ResponseEntity.ok(apiResponse);
            } else {
                InventoryEntity bookEntity = InventoryEntity.builder()
                        .bookId(bookId)
                        .bookPrice(request.getBookPrice())
                        .bookQuantity(request.getBookQuantity())
                        .build();
                InventoryEntity savedInventoryEntity = inventoryRepository.save(bookEntity);
                APIResponse<InventoryEntity> apiResponse = new APIResponse<InventoryEntity>(savedInventoryEntity, null);
                return ResponseEntity.ok(apiResponse);
            }
        } catch (Exception e) {
            throw new InventoryServiceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<APIResponse<?>> fetchId(Long bookId) {
        try {
            Optional<InventoryEntity> findIdInventory = inventoryRepository.findById(bookId);
            if (findIdInventory.isPresent()) {
                APIResponse<InventoryEntity> apiResponse = APIResponse.<InventoryEntity>builder()
                        .data(findIdInventory.get())
                        .build();
                return ResponseEntity.ok(apiResponse);
            } else {
                throw new InventoryServiceException("Not Found");
            }
        } catch (Exception e) {
            throw new InventoryServiceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<APIResponse<?>> fetchAllData(List<Long> ids) {
        var list = inventoryRepository.findAllById(ids);
        APIResponse<List<InventoryEntity>> apiResponse = APIResponse.<List<InventoryEntity>>builder().data(list).build();
        return ResponseEntity.ok(apiResponse);
    }

    public ResponseEntity<APIResponse<?>> deleteInventoryById(Long bookId) {
        try {
            if (inventoryRepository.existsById(bookId)) {
                inventoryRepository.deleteById(bookId);
                APIResponse<String> apiResponse = APIResponse.<String>builder()
                        .data("Successfully deleted")
                        .build();
                return ResponseEntity.ok(apiResponse);
            } else {
                throw new InventoryServiceException("Not Found");
            }
        } catch (Exception e) {
            throw new InventoryServiceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<APIResponse<?>> deductQuantity(BuyBookRequest buyBookRequest) {
        try {
            Optional<InventoryEntity> updateInventory = inventoryRepository.findById(buyBookRequest.getId());
            if (updateInventory.isEmpty()) {
                throw new InventoryServiceException("No Book with matching id found on inventory");
            }
            InventoryEntity inventoryEntity = updateInventory.get();
            // Update the book entity with the new values from the request model
            if (inventoryEntity.getBookQuantity() < buyBookRequest.getQuantity()) {
                throw new InventoryServiceException("Exceeded Quantity");
            }
            inventoryEntity.setBookQuantity(inventoryEntity.getBookQuantity() - buyBookRequest.getQuantity());
            // Save the updated book entity
            InventoryEntity updatedBook = inventoryRepository.save(inventoryEntity);
            APIResponse<InventoryEntity> apiResponse = new APIResponse<>(updatedBook, null);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            throw new InventoryServiceException(e.getMessage());
        }
    }
}