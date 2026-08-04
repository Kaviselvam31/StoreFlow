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

    // =========================
    // SAVE PRODUCT
    // =========================
    public Product saveProduct(Product product) {

        // Automatically set ACTIVE status for new products
        product.setStatus("ACTIVE");

        return productRepository.save(product);
    }

    // =========================
    // GET ALL PRODUCTS
    // =========================
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // =========================
    // GET PRODUCT BY ID
    // =========================
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    // =========================
    // UPDATE PRODUCT
    // =========================
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

        // Keep current status if not provided
        if (product.getStatus() != null) {
            existingProduct.setStatus(product.getStatus());
        }

        return productRepository.save(existingProduct);
    }

    // =========================
    // GET ALL ACTIVE PRODUCTS
    // =========================
    public List<Product> getAllActiveProducts() {
        return productRepository.findAll()
                .stream()
                .filter(product -> "ACTIVE".equalsIgnoreCase(product.getStatus()))
                .toList();
    }

    // =========================
    // SOFT DELETE PRODUCT
    // =========================
    public String deleteProduct(int id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        product.setStatus("INACTIVE");

        productRepository.save(product);

        return "Product Soft Deleted Successfully";
    }
}