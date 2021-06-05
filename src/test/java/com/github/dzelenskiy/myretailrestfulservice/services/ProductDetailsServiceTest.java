package com.github.dzelenskiy.myretailrestfulservice.services;

import com.github.dzelenskiy.myretailrestfulservice.MyretailRestfulServiceApplication;
import com.github.dzelenskiy.myretailrestfulservice.dtos.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductDetailsServiceTest {

    @Autowired
    private ProductDetailsService productDetailsService;

    @Test
    public void getProductDetailsById() {

        ProductDetails productDetails = new ProductDetails();
        productDetails.setId(13860428);
        productDetails.setName("The Big Lebowski (Blu-ray)");

        ProductDetails productDetailsFromService = productDetailsService.getProductDetailsById(13860428);

        assertThat(productDetailsFromService.getId()).isEqualTo(productDetails.getId());
        assertThat(productDetailsFromService.getName()).isEqualTo(productDetails.getName());

    }

}
