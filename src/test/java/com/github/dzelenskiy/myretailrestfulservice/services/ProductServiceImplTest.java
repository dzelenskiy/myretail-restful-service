package com.github.dzelenskiy.myretailrestfulservice.services;

import com.github.dzelenskiy.myretailrestfulservice.MyretailRestfulServiceApplication;
import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:application.properties")
@SpringBootTest(
        //webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = { MyretailRestfulServiceApplication.class }
)
//@AutoConfigureMockMvc(addFilters = false)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductService productService = new ProductServiceImpl();

    public void getProductById() {

        Product product = new Product();
        product.setId(13860428);
        product.setName("The Big Lebowski (Blu-ray)");

        Product productFromService = productService.getProductById(13860428);

        assertThat(productFromService.getId()).isEqualTo(product.getId());
        assertThat(productFromService.getName()).isEqualTo(product.getName());

    }

}
