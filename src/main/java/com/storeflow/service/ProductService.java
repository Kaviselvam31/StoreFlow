package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.Product;
import com.storeflow.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // SAVE
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // GET ALL
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET BY ID
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    // UPDATE
    public Product updateProduct(int id, Product product) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        existingProduct.setProductName(product.getProductName());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setCategoryId(product.getCategoryId());
        existingProduct.setSupplierId(product.getSupplierId());
        existingProduct.setBarcode(product.getBarcode());
        existingProduct.setMrp(product.getMrp());
        existingProduct.setSellingPrice(product.getSellingPrice());
        existingProduct.setDiscountPercentage(product.getDiscountPercentage());
        existingProduct.setManufactureDate(product.getManufactureDate());
        existingProduct.setExpiryDate(product.getExpiryDate());
        existingProduct.setWeight(product.getWeight());
        existingProduct.setUnit(product.getUnit());
        existingProduct.setDescription(product.getDescription());

        return productRepository.save(existingProduct);
    }

    // DELETE
    public String deleteProduct(int id) {

        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return "Product Deleted Successfully";
        }

        return "Product Not Found";
    }
}