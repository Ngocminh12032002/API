package com.bezkoder.springjwt.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.models.ResponseObject;
import com.bezkoder.springjwt.repository.ProductRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	private ProductRepository repository;

	@GetMapping("/all")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Product> allAccess() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	// Let's return an object with: data, message, status
	ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		Optional<Product> foundProduct = repository.findById(id);
		return foundProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Query product successfully", foundProduct)
				// you can replace "ok" with your defined "error code"
				)
				: ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject("failed", "Cannot find product with id = " + id, ""));
	}
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@PostMapping("/insert")
	ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
		// 2 products must not have the same name !
		List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
		if (foundProducts.size() > 0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Product name already taken", ""));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Insert Product successfully", repository.save(newProduct)));
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updatedProduct = repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setYear(newProduct.getYear());
                    product.setPrice(newProduct.getPrice());
                    return repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update Product successfully", updatedProduct)
        );
    }
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if(exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", "Cannot find product to delete", "")
        );
    }

}
