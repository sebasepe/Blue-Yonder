package com.blueyonder.platform.u202220783.scm.domain.model.aggregates;

import com.blueyonder.platform.u202220783.scm.domain.model.commands.CreateInventoryItemCommand;
import com.blueyonder.platform.u202220783.scm.domain.model.commands.OrderUnitsFromItemBySkuIdentifierCommand;
import com.blueyonder.platform.u202220783.scm.domain.model.entities.InventoryItemStatus;
import com.blueyonder.platform.u202220783.scm.domain.model.events.MinimumQuantityThresholdReachedEvent;
import com.blueyonder.platform.u202220783.shared.domain.model.valueobjects.SkuIdentifier;
import com.blueyonder.platform.u202220783.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * InventoryItem Aggregate Root
 */
@NoArgsConstructor
@Entity
public class InventoryItem extends AuditableAbstractAggregateRoot<InventoryItem> {
    @Getter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "sku_identifier"))
    })
    private SkuIdentifier skuIdentifier;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "status_id", nullable = false, unique = false)
    private InventoryItemStatus status;

    @Getter
    @Setter
    private Double minimumQuantity;

    @Getter
    @Setter
    private Double availableQuantity;

    @Getter
    @Setter
    private Double reservedQuantity;

    @Getter
    @Setter
    private Double pendingSupplyQuantity;

    public InventoryItem(CreateInventoryItemCommand command) {
        if(command.minimumQuantity() <= 10) throw new IllegalArgumentException("Minimum quantity for new inventory item must be at greater than 10.");
        if(command.availableQuantity() < 3 * command.minimumQuantity()) throw new IllegalArgumentException("Available quantity for new inventory item must be at least triple of minimum quantity. Triple of inputted minimum quantity: " + command.minimumQuantity() * 3);

        skuIdentifier = new SkuIdentifier(command.skuIdentifier());
        minimumQuantity = command.minimumQuantity();
        availableQuantity = command.availableQuantity();
        reservedQuantity = 0D;
        pendingSupplyQuantity = 0D;
    }

    public boolean isThereEnoughStockForOrder(OrderUnitsFromItemBySkuIdentifierCommand command) {
        if(command.requestedQuantity() <= availableQuantity){
            availableQuantity -= command.requestedQuantity();
            reservedQuantity += command.requestedQuantity();

            emitRestockAlertIfRequired(command);

            return true;
        }
        else {
            reservedQuantity += availableQuantity;
            pendingSupplyQuantity = command.requestedQuantity() - availableQuantity;
            availableQuantity = 0D;

            emitRestockAlertIfRequired(command);

            return false;
        }
    }

    public boolean requiresRestockUnits() {
        return availableQuantity < minimumQuantity;
    }

    private void emitRestockAlertIfRequired(OrderUnitsFromItemBySkuIdentifierCommand command) {
        if (requiresRestockUnits()) {
            Double requiredQuantity = minimumQuantity + pendingSupplyQuantity;
            this.registerEvent(
                    new MinimumQuantityThresholdReachedEvent(
                            this,
                            command.skuIdentifier(),
                            requiredQuantity
                    )
            );
        }
    }
}


