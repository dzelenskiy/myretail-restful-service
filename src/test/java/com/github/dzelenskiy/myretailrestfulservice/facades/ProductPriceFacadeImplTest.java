package com.github.dzelenskiy.myretailrestfulservice.facades;

import com.github.dzelenskiy.myretailrestfulservice.MyretailRestfulServiceApplication;
import com.github.dzelenskiy.myretailrestfulservice.repos.dynamodb.CurrentPriceRepo;
import com.github.dzelenskiy.myretailrestfulservice.services.CurrentPriceService;
import com.github.dzelenskiy.myretailrestfulservice.services.CurrentPriceServiceImpl;
import com.github.dzelenskiy.myretailrestfulservice.services.ProductDetailsService;
import com.github.dzelenskiy.myretailrestfulservice.services.ProductDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:application.properties")
@SpringBootTest(
        //webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = { MyretailRestfulServiceApplication.class }
)
//@AutoConfigureMockMvc(addFilters = false)
public class ProductPriceFacadeImplTest {

    @Mock
    private ProductDetailsService productDetailsService = new ProductDetailsServiceImpl();

    @Mock
    private CurrentPriceService currentPriceService = new CurrentPriceServiceImpl();

    @InjectMocks
    private ProductPriceFacade productPriceFacade = new ProductPriceFacadeImpl();

    @Test
    public void getProductWithCurrentPrice() {

    }


}
