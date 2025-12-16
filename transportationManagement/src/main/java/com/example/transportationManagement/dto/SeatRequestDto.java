package com.example.transportationManagement.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatRequestDto {
    private int seatNumber;
    private Long tripId;
}