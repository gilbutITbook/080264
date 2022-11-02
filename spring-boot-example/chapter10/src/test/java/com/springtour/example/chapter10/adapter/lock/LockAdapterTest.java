package com.springtour.example.chapter10.adapter.lock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class LockAdapterTest {


    private final Long firstUserId = 1L;
    private final Long secondUserId = 2L;
    private final Long thirdUserId = 3L;

    @Autowired
    private LockAdapter lockAdapter;

    @Test
    @DisplayName("firstUserId가 락을 선점한다.")
    public void testLock() {
        final Long hotelId = 123123123L;

        Boolean isSuccess = lockAdapter.holdLock(hotelId, firstUserId);
        Assertions.assertTrue(isSuccess);

        isSuccess = lockAdapter.holdLock(hotelId, secondUserId);
        Assertions.assertFalse(isSuccess);

        isSuccess = lockAdapter.holdLock(hotelId, thirdUserId);
        Assertions.assertFalse(isSuccess);

        Long holderId = lockAdapter.checkLock(hotelId);
        Assertions.assertEquals(firstUserId, holderId);
    }

    @Test
    @DisplayName("3명이 동시에 락을 선점하지만 1명만 락을 잡는다.")
    public void testConcurrentAccess() throws InterruptedException {

        final Long hotelId = 9999999L;
        lockAdapter.clearLock(hotelId);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        new Thread(new Accessor(hotelId, firstUserId, cyclicBarrier)).start();
        new Thread(new Accessor(hotelId, secondUserId, cyclicBarrier)).start();
        new Thread(new Accessor(hotelId, thirdUserId, cyclicBarrier)).start();
        TimeUnit.SECONDS.sleep(1);

        Long holderId = lockAdapter.checkLock(hotelId);
        Assertions.assertTrue(List.of(firstUserId, secondUserId, thirdUserId).contains(holderId));

        lockAdapter.clearLock(hotelId);
    }

    class Accessor implements Runnable {

        private Long hotelId;
        private Long userId;
        private CyclicBarrier cyclicBarrier;

        public Accessor(Long hotelId, Long userId, CyclicBarrier cyclicBarrier) {
            this.hotelId = hotelId;
            this.userId = userId;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                lockAdapter.holdLock(hotelId, userId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
