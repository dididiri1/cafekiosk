package sample.cafekiosk.spring.api.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sample.cafekiosk.spring.api.ApiResponse;
import sample.cafekiosk.spring.api.controller.product.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.ProductService;
import sample.cafekiosk.spring.api.service.product.reponse.ProductResponse;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/v1/products/new")
    public ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductCreateRequest request) {
        System.out.println("cc");
        return ApiResponse.ok(productService.createProduct(request));
    }

    @GetMapping("/api/v1/products/selling")
    public ApiResponse<List<ProductResponse>> getSellingProducts() {
        return ApiResponse.ok(productService.getSellingProducts());
    }
}
