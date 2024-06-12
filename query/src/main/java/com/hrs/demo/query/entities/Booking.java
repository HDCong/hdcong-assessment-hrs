package com.hrs.demo.query.entities;

import com.hrs.demo.query.entities.enums.BookingStatus;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "bookings")
public class Booking implements Serializable {

  @Id
  private Long id;

  @Field(type = FieldType.Nested, includeInParent = true)
  private List<RoomTypeBooked> roomTypes;

  @Field(type = FieldType.Object, includeInParent = true)
  private User user;

  @Field(type = FieldType.Date, name = "startDate")
  private LocalDate startDate;

  @Field(type = FieldType.Date, name = "endDate")
  private LocalDate endDate;

  @Field(type = FieldType.Double, name = "totalBasePrice")
  private Double totalBasePrice;

  @Field(type = FieldType.Text, name = "status")
  private BookingStatus status;
}
