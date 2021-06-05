package com.github.dzelenskiy.myretailrestfulservice.services;

import com.github.dzelenskiy.myretailrestfulservice.MyretailRestfulServiceApplication;
import com.github.dzelenskiy.myretailrestfulservice.dtos.CurrentPrice;
import com.github.dzelenskiy.myretailrestfulservice.repos.dynamodb.CurrentPriceRepo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static java.util.Collections.singletonList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:application.properties")
@SpringBootTest(
        //webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = { MyretailRestfulServiceApplication.class }
)
//@AutoConfigureMockMvc(addFilters = false)
public class CurrentPriceServiceTest {

    @Mock
    private CurrentPriceRepo currentPriceRepo;

    @InjectMocks
    private CurrentPriceService currentPriceService;

    public void getCurrentPriceByProductId() {

        CurrentPrice currentPrice = new CurrentPrice();
        currentPrice.setProductId(13860428);
        currentPrice.setValue(new BigDecimal("7.99"));
        currentPrice.setCurrencyCode("USD");

        when(currentPriceRepo.findByProductId(13860428)).thenReturn(singletonList(currentPrice));

        CurrentPrice currentPriceFromService = currentPriceService.findCurrentPriceByProductId(13860428);

        assertThat(currentPriceFromService.getProductId()).isEqualTo(currentPrice.getProductId());
        assertThat(currentPriceFromService.getValue()).isEqualTo(currentPrice.getValue());
        assertThat(currentPriceFromService.getCurrencyCode()).isEqualTo(currentPrice.getCurrencyCode());

    }



}
