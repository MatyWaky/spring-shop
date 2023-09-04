package matywaky.com.github.springshop.service.product;

import matywaky.com.github.springshop.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAllProducts();
}
