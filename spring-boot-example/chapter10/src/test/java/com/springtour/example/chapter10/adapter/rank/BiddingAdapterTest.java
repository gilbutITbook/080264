package com.springtour.example.chapter10.adapter.rank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BiddingAdapterTest {

    private final Long firstUserId = 1L;
    private final Long secondUserId = 2L;
    private final Long thirdUserId = 3L;
    private final Long fourthUserId = 4L;
    private final Long fifthUserId = 5L;
    private final Long hotelId = 1000L;

    @Autowired
    private BiddingAdapter biddingAdapter;

    @Test
    public void simulate() {
        biddingAdapter.clear(hotelId);

        biddingAdapter.createBidding(hotelId, firstUserId, 100d);
        biddingAdapter.createBidding(hotelId, secondUserId, 110d);
        biddingAdapter.createBidding(hotelId, thirdUserId, 120d);
        biddingAdapter.createBidding(hotelId, fourthUserId, 130d);
        biddingAdapter.createBidding(hotelId, fifthUserId, 140d);

        biddingAdapter.createBidding(hotelId, secondUserId, 150d);
        biddingAdapter.createBidding(hotelId, firstUserId, 200d);

        List<Long> topBidders = biddingAdapter.getTopBidders(hotelId, 3);

        Assertions.assertEquals(firstUserId, topBidders.get(0));
        Assertions.assertEquals(secondUserId, topBidders.get(1));
        Assertions.assertEquals(fifthUserId, topBidders.get(2));

        Assertions.assertEquals(200d, biddingAdapter.getBidAmount(hotelId, firstUserId));
        Assertions.assertEquals(150d, biddingAdapter.getBidAmount(hotelId, secondUserId));
        Assertions.assertEquals(140d, biddingAdapter.getBidAmount(hotelId, fifthUserId));
    }
}
