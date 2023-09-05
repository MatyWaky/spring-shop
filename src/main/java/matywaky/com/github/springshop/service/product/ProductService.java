package matywaky.com.github.springshop.service.product;

import matywaky.com.github.springshop.dto.ProductDto;
import matywaky.com.github.springshop.model.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAllProducts();

    Product findProductByName(String name);

    void saveProduct(ProductDto productDto);
}
