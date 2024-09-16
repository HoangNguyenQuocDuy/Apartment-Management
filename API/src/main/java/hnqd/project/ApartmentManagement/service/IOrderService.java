package hnqd.project.ApartmentManagement.service;

import hnqd.project.ApartmentManagement.dto.OrderRequest;
import hnqd.project.ApartmentManagement.entity.Order;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IOrderService {

    Order createOrder(OrderRequest order) throws IOException;

    Order updateOrder(int orderId);

    List<Order> getOrders(Map<String, String> params);

    Order getOrderById(int lockerId);

    void deleteOrder(int orderId);
}
