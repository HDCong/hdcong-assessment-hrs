package com.hrs.demo.query.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Field(type = FieldType.Long)
  private Long id;

  @Field(type = FieldType.Text)
  private String name;

  @Field(type = FieldType.Text)
  private String email;

  @Field(type = FieldType.Text)
  private String phoneNumber;
}
