package com.hrs.demo.command.entities;

import com.hrs.demo.command.entities.base.AuditedEntity;
import com.hrs.demo.command.entities.enums.BookingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking extends AuditedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @Enumerated(value = EnumType.STRING)
  private BookingStatus status;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column
  private LocalDate startDate;

  @Column
  private LocalDate endDate;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "booking_room_type",
      joinColumns = @JoinColumn(name = "booking_id"),
      inverseJoinColumns = @JoinColumn(name = "room_type_id")
  )
  private List<RoomType> roomTypes;

  @Column
  private BigDecimal totalBasePrice;
}
