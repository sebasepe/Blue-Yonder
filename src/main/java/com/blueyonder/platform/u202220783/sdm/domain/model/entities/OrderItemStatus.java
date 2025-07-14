package com.blueyonder.platform.u202220783.sdm.domain.model.entities;

import com.blueyonder.platform.u202220783.sdm.domain.model.valueobjects.OrderItemStatuses;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

/**
 * OrderItemStatus entity
 * <p>
 *     This entity represents the orderItemStatus of a user in the system.
 *     It is used to define the permissions of a user.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class OrderItemStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderItemStatuses name;

    public OrderItemStatus(OrderItemStatuses name) {
        this.name = name;
    }

    /**
     * Get the name of the orderItemStatus as a string
     * @return the name of the orderItemStatus as a string
     */
    public String getStringName() {
        return name.name();
    }

    /**
     * Get the default orderItemStatus
     * @return the default orderItemStatus
     */
    public static OrderItemStatus getDefaultOrderItemStatus() {
        return new OrderItemStatus(OrderItemStatuses.DISPATCHING);
    }

    /**
     * Get the orderItemStatus from its name
     * @param name the name of the orderItemStatus
     * @return the orderItemStatus
     */
    public static OrderItemStatus toOrderItemStatusFromName(String name) {
        return new OrderItemStatus(OrderItemStatuses.valueOf(name));
    }

    /**
     * Validate the orderItemStatus set
     * <p>
     *     This method validates the orderItemStatus set and returns the default orderItemStatus if the set is empty.
     * </p>
     * @param orderItemStatuses the orderItemStatus set
     * @return the orderItemStatus set
     */
    public static List<OrderItemStatus> validateOrderItemStatusSet(List<OrderItemStatus> orderItemStatuses) {
        if (orderItemStatuses == null || orderItemStatuses.isEmpty()) {
            return List.of(getDefaultOrderItemStatus());
        }
        return orderItemStatuses;
    }

}


