package com.myproject.ws.core;

import java.math.BigDecimal;

public class ProductCreatedEvent {

    private String productId;
    private String title ;
    private BigDecimal price;
    private Integer quantity;

    //Need noArgs Constructor for deserialization process
    public ProductCreatedEvent() {

    }

    public ProductCreatedEvent(String productId, String title, BigDecimal price, Integer quantity) {
    this.productId = productId;
    this.price=price;
    this.quantity = quantity;
    this.title= title;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
