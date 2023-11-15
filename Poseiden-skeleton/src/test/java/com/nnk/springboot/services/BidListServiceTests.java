package com.nnk.springboot.services;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BidListServiceTests {

    @InjectMocks
    private BidListService bidListService;

    @Mock
    private BidListRepository bidListRepository;

    @Test
    public void findAllBidListsTest() {
        // GIVEN
        BidList bidList1 = createBidList();
        BidList bidList2 = createBidList();
        List<BidList> expectedBidLists = Arrays.asList(bidList1, bidList2);
        when(bidListRepository.findAll()).thenReturn(expectedBidLists);

        // WHEN
        List<BidList> actualBidLists = bidListService.findAll();

        // THEN
        assertThat(actualBidLists).isEqualTo(expectedBidLists);
    }
    @Test
    public void saveBidListTest() {
        // GIVEN
        BidList bid = createBidList();
        // WHEN
        bidListService.save(bid);
        // THEN
        verify(bidListRepository, times(1)).save(bid);
    }

    @Test
    public void findByIdBidListTest() {
        // GIVEN
        BidList bid = createBidList();
        // WHEN
        when(bidListRepository.findById(1)).thenReturn(java.util.Optional.of(bid));
        BidList bidList = bidListService.findById(1);
        // THEN
        assertBidListEquals(bid, bidList);
    }

    @Test
    public void updateBidListTest() {
        // GIVEN
        BidList bid = createBidList();
        bidListService.save(bid);
        bid.setBidQuantity(20d);
        // WHEN
        bidListService.save(bid);
        // THEN
        verify(bidListRepository, times(2)).save(bid);
    }

    @Test
    public void deleteByIdBidListTest() {
        // GIVEN
        // WHEN
        bidListService.deleteById(1);
        // THEN
        verify(bidListRepository, times(1)).deleteById(1);
    }

    private BidList createBidList() {
        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setCreationDate(new Timestamp(new Date().getTime()));
        bid.setBidQuantity(1.0);
        bid.setAskQuantity(1.0);
        bid.setBid(1.0);
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        return bid;
    }

    private void assertBidListEquals(BidList expected, BidList actual) {
        assertThat(actual.getBidListId()).isEqualTo(expected.getBidListId());
        assertThat(actual.getAccount()).isEqualTo(expected.getAccount());
        assertThat(actual.getType()).isEqualTo(expected.getType());
    }
}
