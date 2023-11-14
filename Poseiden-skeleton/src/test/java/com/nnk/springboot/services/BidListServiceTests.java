package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class BidListServiceTests {

    @InjectMocks
    private BidListService bidListService;

    @Mock
    BidListRepository bidListRepository;


    @Test
    public void saveBidListTest() {
        //GIVEN
        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setCreationDate(new Timestamp(new Date().getTime()));
        bid.setBidQuantity(1.0);
        bid.setAskQuantity(1.0);
        bid.setBid(1.0);
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        //WHEN
        bidListService.save(bid);
        //THEN
        verify(bidListRepository,times(1)).save(bid);
    }
    @Test
    public void FindByIdBidListTest() {
        //GIVEN
        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setCreationDate(new Timestamp(new Date().getTime()));
        bid.setBidQuantity(1.0);
        bid.setAskQuantity(1.0);
        bid.setBid(1.0);
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        //WHEN
        when(bidListRepository.findById(1)).thenReturn(java.util.Optional.of(bid));
        BidList bidList = bidListService.findById(1);
        //THEN
        assertThat(bidList.getBidListId()).isEqualTo(1);
        assertThat(bidList.getAccount()).isEqualTo(bid.getAccount());
        assertThat(bidList.getType()).isEqualTo(bid.getType());
    }
    @Test
    public void UpdateBidListTest(){
        //GIVEN
        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setCreationDate(new Timestamp(new Date().getTime()));
        bid.setBidQuantity(1.0);
        bid.setAskQuantity(1.0);
        bid.setBid(1.0);
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        bidListService.save(bid);
        bid.setBidQuantity(20d);
        //WHEN
        bidListService.save(bid);
        //THEN
        verify(bidListRepository,times(2)).save(bid);
    }
    @Test
    public void DeleteByIdBidListTest(){
        //GIVEN
        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setCreationDate(new Timestamp(new Date().getTime()));
        bid.setBidQuantity(1.0);
        bid.setAskQuantity(1.0);
        bid.setBid(1.0);
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        // WHEN
        bidListService.deleteById(1);
        // THEN
        verify(bidListRepository,times(1)).deleteById(1);
    }

}

