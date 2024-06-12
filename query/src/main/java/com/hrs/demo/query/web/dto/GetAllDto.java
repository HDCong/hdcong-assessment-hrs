package com.hrs.demo.query.web.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GetAllDto<T> {
  @Builder.Default
  private Long total = 0L;
  @Builder.Default
  private List<T> content = new ArrayList<>();
}