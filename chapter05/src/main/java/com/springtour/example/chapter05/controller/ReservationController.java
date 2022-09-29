package com.springtour.example.chapter05.controller;

import com.springtour.example.chapter05.domain.FileDownloadException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

@RestController
public class ReservationController {

    @GetMapping("/hotels/{hotelId}/rooms/{roomNumber}/reservations")
    public List<Long> getReservationsByPaging(@PathVariable Long hotelId,
                                              @PathVariable String roomNumber,
                                              Pageable pageable) {

        System.out.println("Page param : " + pageable.getPageNumber());
        System.out.println("Size param : " + pageable.getPageSize());
        pageable.getSort().stream().forEach(order -> {
            System.out.println("Sort param : " + order.getProperty() + " : " + order.getDirection());
        });

        return Collections.emptyList();
    }

    @GetMapping(value = "/hotels/{hotelId}/rooms/{roomNumber}/reservations/{reservationId}", produces = "application/pdf")
    public void downloadInvoice(@PathVariable Long hotelId,
                                @PathVariable String roomNumber,
                                @PathVariable Long reservationId,
                                HttpServletResponse response) {

        String filePath = "pdf/hotel_invoice.pdf";
        try (InputStream is = new ClassPathResource(filePath).getInputStream();
             OutputStream os = response.getOutputStream();) {

            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setHeader("Content-Disposition", "filename=hotel_invoice.pdf");

            StreamUtils.copy(is, os);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new FileDownloadException("file download error");
        }
    }

    @GetMapping(value = "/hotels/{hotelId}/rooms/{roomNumber}/reservations/{reservationId}")
    public ResponseEntity<byte[]> getInvoice(@PathVariable Long hotelId,
                                             @PathVariable String roomNumber,
                                             @PathVariable Long reservationId) {

        String filePath = "pdf/hotel_invoice.pdf";
        try (InputStream inputStream = new ClassPathResource(filePath).getInputStream()) {
            byte[] bytes = StreamUtils.copyToByteArray(inputStream);
            return new ResponseEntity<>(bytes, HttpStatus.OK);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new FileDownloadException("file download error");
        }
    }
}
