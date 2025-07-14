package com.blueyonder.platform.u202220783.scm.interfaces.acl;

/**
 * SCMContextFacade
 */
public interface SCMContextFacade {
    /**
     * Checks if a given sku identifier corresponds to an existing inventory item
     * @param skuIdentifier the unique sku identifier of the inventory item
     * @return true if it exists, false otherwise
     */
    boolean existsBySkuIdentifier(String skuIdentifier);

    boolean orderUnitsFromInventoryItemBySkuIdentifier(String skuIdentifier, Double requestedQuantity);
}


