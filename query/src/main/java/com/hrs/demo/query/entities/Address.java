package com.hrs.demo.query.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

  @Field(type = FieldType.Text)
  private String district;

  @Field(type = FieldType.Text)
  private String province;

  @Field(type = FieldType.Text)
  private String text;

  @Field(type = FieldType.Text)
  private String ward;

}
