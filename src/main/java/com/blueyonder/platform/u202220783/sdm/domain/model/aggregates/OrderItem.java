package com.blueyonder.platform.u202220783.sdm.domain.model.aggregates;

import com.blueyonder.platform.u202220783.shared.domain.model.valueobjects.SkuIdentifier;
import com.blueyonder.platform.u202220783.sdm.domain.model.commands.CreateOrderItemCommand;
import com.blueyonder.platform.u202220783.sdm.domain.model.entities.OrderItemStatus;
import com.blueyonder.platform.u202220783.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * OrderItem Aggregate Root
 */
@NoArgsConstructor
@Entity
public class OrderItem extends AuditableAbstractAggregateRoot<OrderItem> {
    @Getter
    private Long orderId;

    @Getter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "sku_identifier"))
    })
    private SkuIdentifier skuIdentifier;

    @Getter
    private Double requestedQuantity;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "status_id", nullable = false, unique = false)
    private OrderItemStatus status;

    @Getter
    private Date orderedAt;

    public OrderItem(CreateOrderItemCommand command) {
        if (command.requestedQuantity() <= 0) throw new IllegalArgumentException("Requested quantity for order must be greater than 0.");

        orderId = command.orderId();
        skuIdentifier = new SkuIdentifier(command.skuIdentifier());
        requestedQuantity = command.requestedQuantity();
        orderedAt = command.orderedAt();
    }
}
