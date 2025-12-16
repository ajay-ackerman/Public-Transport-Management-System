package com.example.transportationManagement.dto;
import com.example.transportationManagement.entity.type.SeatStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponseDto {

    private Long id;
    private int seatNumber;
    private SeatStatus status;
}