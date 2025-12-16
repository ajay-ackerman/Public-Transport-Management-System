package com.example.transportationManagement.entity;
import com.example.transportationManagement.entity.type.SeatStatus;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "seats",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"trip_id", "seat_number"})
        })
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;   // AVAILABLE, BOOKED

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

}