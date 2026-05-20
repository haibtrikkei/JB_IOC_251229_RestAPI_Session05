package ra.demo.service;

import org.springframework.data.domain.Page;
import ra.demo.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> insertListProduct(List<Product> list);

    //Khai báo 1 hàm vừa tìm kiếm theo tên sản phẩm, vừa sắp xếp, vừa phân trang
    Page<Product> getProductsByNameAndPagingSorting(String proName, String sortBy, String orderBy, Integer page, Integer size);

    //Khai báo 1 hàm tìm kiếm theo tên sản phẩm và tên nhà sản xuất và khoảng giá
    Page<Product> getProductsByNameProducerPrice(String proName, String producer, Double min, Double max, String sortBy, String orderBy, Integer page, Integer size);
}
