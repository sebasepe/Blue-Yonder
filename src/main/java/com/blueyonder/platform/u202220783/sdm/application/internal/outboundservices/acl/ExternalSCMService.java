package com.blueyonder.platform.u202220783.sdm.application.internal.outboundservices.acl;

import com.blueyonder.platform.u202220783.scm.interfaces.acl.SCMContextFacade;
import org.springframework.stereotype.Service;

/**
 * External SCM Service
 */
@Service
public class ExternalSCMService {
    private final SCMContextFacade scmContextFacade;

    public ExternalSCMService(SCMContextFacade scmContextFacade) {
        this.scmContextFacade = scmContextFacade;
    }

    public boolean existsBySkuIdentifier(String skuIdentifier) {
        return scmContextFacade.existsBySkuIdentifier(skuIdentifier);
    }

    public boolean inventoryHasEnoughUnitsForOrder(String skuIdentifier, Double requestedQuantity) {
        return scmContextFacade.orderUnitsFromInventoryItemBySkuIdentifier(skuIdentifier, requestedQuantity);
    }
}
