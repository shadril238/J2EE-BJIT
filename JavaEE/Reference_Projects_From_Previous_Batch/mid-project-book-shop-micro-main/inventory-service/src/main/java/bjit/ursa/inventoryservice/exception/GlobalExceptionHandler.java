package bjit.ursa.inventoryservice.exception;

import bjit.ursa.inventoryservice.entity.InventoryEntity;
import bjit.ursa.inventoryservice.model.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public  class GlobalExceptionHandler {
    @ExceptionHandler({InventoryServiceException.class})
    public ResponseEntity<APIResponse> InventoryServiceExceptionHandler(Exception ex) {
        APIResponse<InventoryEntity> apiResponse = APIResponse.<InventoryEntity>builder()
                .error_message(ex.getMessage())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}