package com.hrs.demo.command.mapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface ICommonMapper<D, E> {

  E toEntity(D dto);

  D toDto(E entity);

  List<E> toEntity(List<D> dtoList);

  List<D> toDto(List<E> entityList);

  @Named("partialUpdate")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void partialUpdate(@MappingTarget E entity, D dto);

  @Named("long2Date")
  default Date long2Date(Long value) {
    return Optional.ofNullable(value).map(Date::new).orElse(null);
  }

  @Named("date2Long")
  default Long date2Long(Date value) {
    return Optional.ofNullable(value).map(Date::getTime).orElse(null);
  }
}
