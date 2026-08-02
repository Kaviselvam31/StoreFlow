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

    // Create Product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Get All Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get Product By Id
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    // Update Product
    public Product updateProduct(int id, Product updatedProduct) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        product.setProductName(updatedProduct.getProductName());
        product.setBrand(updatedProduct.getBrand());
        product.setCategoryId(updatedProduct.getCategoryId());
        product.setSupplierId(updatedProduct.getSupplierId());
        product.setBarcode(updatedProduct.getBarcode());
        product.setMrp(updatedProduct.getMrp());
        product.setSellingPrice(updatedProduct.getSellingPrice());
        product.setDiscountPercentage(updatedProduct.getDiscountPercentage());
        product.setManufactureDate(updatedProduct.getManufactureDate());
        product.setExpiryDate(updatedProduct.getExpiryDate());
        product.setWeight(updatedProduct.getWeight());
        product.setUnit(updatedProduct.getUnit());
        product.setDescription(updatedProduct.getDescription());

        return productRepository.save(product);
    }

    // Delete Product
    public String deleteProduct(int id) {

        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return "Product Deleted Successfully";
        }

        return "Product Not Found";
    }
}