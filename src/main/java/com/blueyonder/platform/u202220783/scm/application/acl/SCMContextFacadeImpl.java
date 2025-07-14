package com.blueyonder.platform.u202220783.scm.application.acl;

import com.blueyonder.platform.u202220783.scm.domain.model.commands.OrderUnitsFromItemBySkuIdentifierCommand;
import com.blueyonder.platform.u202220783.scm.domain.model.queries.ExistsBySkuIdentifierQuery;
import com.blueyonder.platform.u202220783.scm.domain.services.InventoryItemCommandService;
import com.blueyonder.platform.u202220783.scm.domain.services.InventoryItemQueryService;
import com.blueyonder.platform.u202220783.scm.interfaces.acl.SCMContextFacade;
import org.springframework.stereotype.Service;

@Service
public class SCMContextFacadeImpl implements SCMContextFacade {
    private final InventoryItemCommandService inventoryItemCommandService;
    private final InventoryItemQueryService inventoryItemQueryService;

    public SCMContextFacadeImpl(InventoryItemCommandService inventoryItemCommandService, InventoryItemQueryService inventoryItemQueryService) {
        this.inventoryItemCommandService = inventoryItemCommandService;
        this.inventoryItemQueryService = inventoryItemQueryService;
    }

    @Override
    public boolean existsBySkuIdentifier(String skuIdentifier) {
        return inventoryItemQueryService.handle(
                new ExistsBySkuIdentifierQuery(skuIdentifier)
        );
    }

    @Override
    public boolean orderUnitsFromInventoryItemBySkuIdentifier(String skuIdentifier, Double requestedQuantity) {
        // El agregado retorna true si se pudo manejar la orden, false si no
        // El propio command service en su handle retorna lo mismo que el agregado
        // Si la fachada retorna lo mismo que el command service, entonces si
        // el valor es true, significa que si alcanzaron. Caso contrario no
        return inventoryItemCommandService.handle(
                new OrderUnitsFromItemBySkuIdentifierCommand(skuIdentifier, requestedQuantity)
        );
    }
}


