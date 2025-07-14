package com.blueyonder.platform.u202220783.sdm.infrastructure.persistence.jpa.repositories;

import com.blueyonder.platform.u202220783.sdm.domain.model.entities.OrderItemStatus;
import com.blueyonder.platform.u202220783.sdm.domain.model.valueobjects.OrderItemStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is responsible for providing the OrderItemStatus entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface OrderItemStatusRepository extends JpaRepository<OrderItemStatus, Long> {

    /**
     * This method is responsible for finding the orderItemStatus by name.
     * @param name The orderItemStatus name.
     * @return The orderItemStatus object.
     */
    Optional<OrderItemStatus> findByName(OrderItemStatuses name);

    /**
     * This method is responsible for checking if the orderItemStatus exists by name.
     * @param name The orderItemStatus name.
     * @return True if the orderItemStatus exists, false otherwise.
     */
    boolean existsByName(OrderItemStatuses name);

}


