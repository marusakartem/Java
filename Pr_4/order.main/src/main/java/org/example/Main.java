package org.example;

import org.example.order.order.processing.OrderManager;
import org.example.order.order.product.Clothing;
import org.example.order.order.product.Electronics;
import org.example.order.order.product.Product;
import org.example.order.storage.OrderStorage;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderStorage orderStorage = new OrderStorage();

        orderStorage.addOrder(new Electronics("Smartphone"));
        orderStorage.addOrder(new Clothing("T-shirt"));

        OrderManager orderManager = new OrderManager();
        List<Product> orders = orderStorage.getOrders();
        orderManager.processOrders(orders);
    }
}
