package com.blueyonder.platform.u202220783.sdm.interfaces.rest;

import com.blueyonder.platform.u202220783.sdm.domain.services.OrderItemCommandService;
import com.blueyonder.platform.u202220783.sdm.interfaces.rest.resources.CreateOrderItemResource;
import com.blueyonder.platform.u202220783.sdm.interfaces.rest.resources.OrderItemResource;
import com.blueyonder.platform.u202220783.sdm.interfaces.rest.transform.CreateOrderItemCommandFromResourceAssembler;
import com.blueyonder.platform.u202220783.sdm.interfaces.rest.transform.OrderItemResourceFromEntityAssembler;
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
 * OrderItemsController
 */
@RestController
@RequestMapping(value = "/api/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "OrderItems", description = "Available OrderItem Endpoints")
public class OrderItemsController {
    private final OrderItemCommandService orderItemCommandService;

    /**
     * Constructor
     * @param orderItemCommandService The {@link OrderItemCommandService} instance
     */
    public OrderItemsController(OrderItemCommandService orderItemCommandService) {
        this.orderItemCommandService = orderItemCommandService;
    }

    /**
     * Create a new orderItem
     * @param resource The {@link CreateOrderItemResource} instance
     * @return A {@link OrderItemResource} resource for the created orderItem, or a bad request response if the orderItem could not be created.
     */
    @PostMapping("/{orderId}/items")
    @Operation(summary = "Create a new orderItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OrderItem created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<OrderItemResource> createOrderItem(@RequestBody CreateOrderItemResource resource, @PathVariable Long orderId) {
        var createOrderItemCommand = CreateOrderItemCommandFromResourceAssembler.toCommandFromResource(resource, orderId);
        var orderItem = orderItemCommandService.handle(createOrderItemCommand);
        if (orderItem.isEmpty()) return ResponseEntity.badRequest().build();
        var createdOrderItem = orderItem.get();
        var orderItemResource = OrderItemResourceFromEntityAssembler.toResourceFromEntity(createdOrderItem);
        return new ResponseEntity<>(orderItemResource, HttpStatus.CREATED);
    }
}


