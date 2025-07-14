package com.blueyonder.platform.u202220783.scm.interfaces.rest;

import com.blueyonder.platform.u202220783.scm.domain.services.InventoryItemCommandService;
import com.blueyonder.platform.u202220783.scm.interfaces.rest.resources.CreateInventoryItemResource;
import com.blueyonder.platform.u202220783.scm.interfaces.rest.resources.InventoryItemResource;
import com.blueyonder.platform.u202220783.scm.interfaces.rest.transform.CreateInventoryItemCommandFromResourceAssembler;
import com.blueyonder.platform.u202220783.scm.interfaces.rest.transform.InventoryItemResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * InventoryItemsController
 */
@RestController
@RequestMapping(value = "/api/v1/inventory-items", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "InventoryItems", description = "Available InventoryItem Endpoints")
public class InventoryItemsController {
    private final InventoryItemCommandService inventoryItemCommandService;

    /**
     * Constructor
     * @param inventoryItemCommandService The {@link InventoryItemCommandService} instance
     */
    public InventoryItemsController(InventoryItemCommandService inventoryItemCommandService) {
        this.inventoryItemCommandService = inventoryItemCommandService;
    }

    /**
     * Create a new inventoryItem
     * @param resource The {@link CreateInventoryItemResource} instance
     * @return A {@link InventoryItemResource} resource for the created inventoryItem, or a bad request response if the inventoryItem could not be created.
     */
    @PostMapping
    @Operation(summary = "Create a new inventoryItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "InventoryItem created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<InventoryItemResource> createInventoryItem(@RequestBody CreateInventoryItemResource resource) {
        var createInventoryItemCommand = CreateInventoryItemCommandFromResourceAssembler.toCommandFromResource(resource);
        var inventoryItem = inventoryItemCommandService.handle(createInventoryItemCommand);
        if (inventoryItem.isEmpty()) return ResponseEntity.badRequest().build();
        var createdInventoryItem = inventoryItem.get();
        var inventoryItemResource = InventoryItemResourceFromEntityAssembler.toResourceFromEntity(createdInventoryItem);
        return new ResponseEntity<>(inventoryItemResource, HttpStatus.CREATED);
    }
}
