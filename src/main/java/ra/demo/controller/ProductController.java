package ra.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.demo.model.dto.response.ApiDataResponse;
import ra.demo.model.entity.Product;
import ra.demo.service.ProductService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @Value("${pageSizeProduct}")
    private Integer size;

    @PostMapping("/init-products")
    public ResponseEntity<ApiDataResponse<List<Product>>> initProducts() {
        List<Product> products = new ArrayList<>();
        Random r = new Random();
        for (int i = 1; i <= 50; i++) {
            products.add(new Product(null, "Sản phẩm " + i, "Nhà sản xuất " + r.nextInt(1, 10), 2018 + r.nextInt(8), r.nextDouble(100000, 1000000)));
        }

        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Khởi tạo danh sách sản phẩm thành công!",
                productService.insertListProduct(products),
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<Product>>> getProducts(@RequestParam(name = "proName", defaultValue = "") String proName,
                                                                      @RequestParam(name = "sortBy", defaultValue = "proId") String sortBy,
                                                                      @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy,
                                                                      @RequestParam(name = "page", defaultValue = "1") Integer page) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy danh sách sản phẩm trang " + page + " thành công!",
                productService.getProductsByNameAndPagingSorting(proName, sortBy, orderBy, page - 1, size),
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping("/name-producer-price")
    public ResponseEntity<ApiDataResponse<Page<Product>>> getProductsByNameProducerPrice(@RequestParam(name = "proName", defaultValue = "") String proName,
                                                                                         @RequestParam(name = "producer", defaultValue = "") String producer,
                                                                                         @RequestParam(name = "min", defaultValue = Double.MIN_VALUE + "") Double min,
                                                                                         @RequestParam(name = "max", defaultValue = Double.MAX_VALUE + "") Double max,
                                                                                         @RequestParam(name = "sortBy", defaultValue = "proId") String sortBy,
                                                                                         @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy,
                                                                                         @RequestParam(name = "page", defaultValue = "1") Integer page) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy danh sách sản phẩm trang " + page + " thành công!",
                productService.getProductsByNameProducerPrice(proName, producer, min, max, sortBy, orderBy, page - 1, size),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}
