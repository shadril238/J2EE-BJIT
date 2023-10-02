package bjit.ursa.inventoryservice.repository;

import bjit.ursa.inventoryservice.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
}