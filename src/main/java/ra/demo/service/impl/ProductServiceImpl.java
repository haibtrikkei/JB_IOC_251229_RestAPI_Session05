package ra.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.demo.model.entity.Product;
import ra.demo.repository.ProductRepository;
import ra.demo.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public List<Product> insertListProduct(List<Product> list) {
        return productRepository.saveAll(list);
    }

    @Override
    public Page<Product> getProductsByNameAndPagingSorting(String proName, String sortBy, String orderBy, Integer page, Integer size) {
        Sort sort;
        Pageable pageable;

        switch (sortBy){
            case "proName":
                sort = Sort.by("proName");
                break;
            case "producer":
                sort = Sort.by("producer");
                break;
            case "yearMaking":
                sort = Sort.by("yearMaking");
                break;
            case "price":
                sort = Sort.by("price");
                break;
            default:
                sort = Sort.by("proId");
        }

        switch (orderBy){
            case "asc":
                sort = sort.ascending();
                break;
            case "desc":
                sort = sort.descending();
                break;
        }

        pageable = PageRequest.of(page,size,sort);
        return productRepository.findAllByProNameContaining(proName,pageable);
    }

    @Override
    public Page<Product> getProductsByNameProducerPrice(String proName, String producer, Double min, Double max, String sortBy, String orderBy, Integer page, Integer size) {
        Sort sort;
        Pageable pageable;

        switch (sortBy){
            case "proName":
                sort = Sort.by("proName");
                break;
            case "producer":
                sort = Sort.by("producer");
                break;
            case "yearMaking":
                sort = Sort.by("yearMaking");
                break;
            case "price":
                sort = Sort.by("price");
                break;
            default:
                sort = Sort.by("proId");
        }

        switch (orderBy){
            case "asc":
                sort = sort.ascending();
                break;
            case "desc":
                sort = sort.descending();
                break;
        }

        pageable = PageRequest.of(page,size,sort);

        return productRepository.getProductsByNameProducerPrice(proName,producer,min,max,pageable);
    }


}
