package com.github.dzelenskiy.myretailrestfulservice.services;

import com.github.dzelenskiy.myretailrestfulservice.MyretailRestfulServiceApplication;
import com.github.dzelenskiy.myretailrestfulservice.dtos.CurrentPrice;
import com.github.dzelenskiy.myretailrestfulservice.enums.CurrencyCode;
import com.github.dzelenskiy.myretailrestfulservice.repos.CurrentPriceMongoRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { MyretailRestfulServiceApplication.class })
@TestPropertySource(locations="classpath:application.properties")
public class CurrentPriceServiceImplTest {

    @Mock
    private CurrentPriceMongoRepo currentPriceMongoRepo;

    @InjectMocks
    private CurrentPriceService currentPriceService = new CurrentPriceServiceImpl();

    @Test
    public void getCurrentPriceByProductId() {

        CurrentPrice currentPrice = new CurrentPrice();
        currentPrice.setProductId(13860428);
        currentPrice.setValue(new BigDecimal("7.99"));
        currentPrice.setCurrencyCode(CurrencyCode.USD.toString());

        when(currentPriceMongoRepo.findByProductId(13860428)).thenReturn(singletonList(currentPrice));

        CurrentPrice currentPriceFromService = currentPriceService.getCurrentPriceByProductId(13860428);

        assertThat(currentPriceFromService.getProductId()).isEqualTo(currentPrice.getProductId());
        assertThat(currentPriceFromService.getValue()).isEqualTo(currentPrice.getValue());
        assertThat(currentPriceFromService.getCurrencyCode()).isEqualTo(currentPrice.getCurrencyCode());

    }

    @Test
    public void updateCurrentPrice() {

        CurrentPrice currentPrice = new CurrentPrice();
        currentPrice.setProductId(12954218);
        currentPrice.setValue(new BigDecimal("1.99"));
        currentPrice.setCurrencyCode(CurrencyCode.USD.toString());

        currentPriceService.updateCurrentPrice(currentPrice);

        verify(currentPriceMongoRepo, times(1)).save(currentPrice);

    }

}
