package org.example.order.order.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public abstract class Product {
    private String name;
}
