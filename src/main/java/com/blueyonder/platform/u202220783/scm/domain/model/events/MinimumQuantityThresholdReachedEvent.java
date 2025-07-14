package com.blueyonder.platform.u202220783.scm.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * MinimumQuantityThresholdReachedEvent
 * Event that represents the completion of a tutorial
 */
@Getter
public class MinimumQuantityThresholdReachedEvent extends ApplicationEvent {
    private final String skuIdentifier;
    private final Double requestedSupplyQuantity;
    /**
     * MinimumQuantityThresholdReachedEvent Constructor
     * @param source The source of the event
     * @param skuIdentifier The enrollment id
     * @param requestedSupplyQuantity The tutorial id
     * @see ApplicationEvent
     */
    public MinimumQuantityThresholdReachedEvent(Object source, String skuIdentifier, Double requestedSupplyQuantity) {
        super(source);
        this.skuIdentifier = skuIdentifier;
        this.requestedSupplyQuantity = requestedSupplyQuantity;
    }
}

