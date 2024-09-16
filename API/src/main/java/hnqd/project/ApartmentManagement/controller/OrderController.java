package hnqd.project.ApartmentManagement.controller;

import hnqd.project.ApartmentManagement.dto.OrderRequest;
import hnqd.project.ApartmentManagement.dto.ResponseObject;
import hnqd.project.ApartmentManagement.entity.Order;
import hnqd.project.ApartmentManagement.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PutMapping("/{orderId}")
    public ResponseEntity<ResponseObject> confirmedOrder(@PathVariable("orderId") int orderId) throws IOException {
        Order orderSave = orderService.updateOrder(orderId);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(
                new ResponseObject("OK", "Update order successfully!", orderSave)
        );
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject> createOrder(
            @RequestPart("file") MultipartFile file,
            @RequestParam Map<String, String> params
    ) throws IOException {
        OrderRequest orderRequest = OrderRequest.builder()
                .lockerId(Integer.parseInt(params.get("lockerId")))
                .file(file)
                .build();

        Order orderSave = orderService.createOrder(orderRequest);

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Create order successfully!", orderSave)
        );
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ResponseObject> deleteOrder(@PathVariable("orderId") int orderId) {
        orderService.deleteOrder(orderId);

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Delete order successfully!", "")
        );
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> getOrders(@RequestParam() Map<String, String> params) {
        List<Order> orders = orderService.getOrders(params);

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(
                new ResponseObject("OK", "Get orders successfully!", orders)
        );
    }
}
