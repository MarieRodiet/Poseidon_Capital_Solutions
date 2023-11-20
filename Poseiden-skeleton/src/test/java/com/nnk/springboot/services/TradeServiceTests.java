package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTests {

    @InjectMocks
    private TradeService tradeService;

    @Mock
    private TradeRepository tradeRepository;


    @Test
    public void findAllTradesTest() {
        // GIVEN
        Trade trade1 = createTrade(1);
        Trade trade2 = createTrade(2);
        List<Trade> expectedTrades = Arrays.asList(trade1, trade2);
        when(tradeRepository.findAll()).thenReturn(expectedTrades);

        // WHEN
        List<Trade> actualTrades = tradeService.findAll();

        // THEN
        assertThat(actualTrades).isEqualTo(expectedTrades);
    }

    @Test
    public void saveTradeTest() {
        // GIVEN
        Trade trade = createTrade(1);

        // WHEN
        tradeService.save(trade);

        // THEN
        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    public void deleteByIdTradeTest() {
        // GIVEN
        int tradeId = 1;

        // WHEN
        tradeService.deleteById(tradeId);

        // THEN
        verify(tradeRepository, times(1)).deleteById(tradeId);
    }

    @Test
    public void findByIdTradeTest() {
        // GIVEN
        int tradeId = 1;
        Trade expectedTrade = createTrade(tradeId);
        when(tradeRepository.findById(tradeId)).thenReturn(Optional.of(expectedTrade));

        // WHEN
        Trade actualTrade = tradeService.findById(tradeId);

        // THEN
        assertThat(actualTrade).isEqualTo(expectedTrade);
    }

    @Test
    public void findByIdTradeNotFoundTest() {
        // GIVEN
        int tradeId = 1;
        when(tradeRepository.findById(tradeId)).thenReturn(Optional.empty());

        // WHEN
        Trade actualTrade = tradeService.findById(tradeId);

        // THEN
        assertThat(actualTrade).isNull();
    }

    private Trade createTrade(int id) {
        Trade trade = new Trade();
        trade.setTradeId(id);
        // Set other trade properties as needed
        return trade;
    }

}
