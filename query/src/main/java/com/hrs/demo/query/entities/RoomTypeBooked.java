package com.hrs.demo.query.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomTypeBooked {
  @Field(type = FieldType.Long)
  private Long id;

  @Field(type = FieldType.Text)
  private String name;

  @Field(type = FieldType.Double)
  private Double basePrice;

  @Field(type = FieldType.Integer)
  private Integer totalRoom;

  @Field(type = FieldType.Object, includeInParent = true)
  private Hotel hotel;
}
