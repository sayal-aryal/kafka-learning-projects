package com.myproject.ws.products.service;

import com.myproject.ws.products.rest.CreateProductRestModel;

public interface ProductService {
    String createProduct(CreateProductRestModel productRestModel) throws Exception;
}
