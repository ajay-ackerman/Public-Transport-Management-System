package com.example.transportationManagement.repository;
import com.example.transportationManagement.entity.Seat;
import com.example.transportationManagement.entity.type.SeatStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT s FROM Seat s
        WHERE s.id IN :seatIds
          AND s.trip.id = :tripId
          AND s.status = 'AVAILABLE'
    """)
    List<Seat> lockAvailableSeats(
            @Param("seatIds") List<Long> seatIds,
            @Param("tripId") Long tripId
    );

    List<Seat> findByTripId(Long tripId);

    List<Seat> findByTripIdAndStatus(Long tripId, SeatStatus seatStatus);
   
}