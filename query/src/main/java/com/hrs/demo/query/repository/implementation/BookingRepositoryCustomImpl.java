package com.hrs.demo.query.repository.implementation;

import com.hrs.demo.query.entities.Booking;
import com.hrs.demo.query.repository.IBookingRepositoryCustom;
import com.hrs.demo.query.web.dto.request.GetAllBookingCriteria;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookingRepositoryCustomImpl implements IBookingRepositoryCustom {

  private final ElasticsearchOperations elasticsearchOperations;


  @Override
  public Page<Booking> search(GetAllBookingCriteria criteria, Pageable pageable) {
    final SearchHits<Booking> searchHits = elasticsearchOperations.search(
        buildQuery(criteria, pageable),
        Booking.class
    );
    return PageableExecutionUtils.getPage(
        searchHits.map(SearchHit::getContent).toList(),
        pageable,
        searchHits::getTotalHits);
  }

  private Query buildQuery(GetAllBookingCriteria criteria, Pageable pageable) {
    StringBuilder queryString = new StringBuilder("{ \"bool\": { ");

    if (StringUtils.isNotBlank(criteria.getUserId())) {
      queryString.append("\"filter\": { ")
          .append("\"term\": { \"user.id\": ").append(criteria.getUserId()).append(" } ")
          .append("}, ");
    }
    final var keyword = criteria.getKeyword();
    if (StringUtils.isNotBlank(keyword)) {
      queryString.append("\"must\": { ")
          .append("\"multi_match\": { ")
          .append("\"query\": \"").append(keyword).append("\", ")
          .append(
              "\"fields\": [\"status\", \"roomTypes.id\", \"roomTypes.name\", \"rooms.hotel.name\", \"rooms.hotel.description\"], ")
          .append("\"type\": \"phrase\" ")
          .append("} ")
          .append("}, ");
    }

    // Remove trailing comma and space if both conditions were added
    if (queryString.toString().endsWith(", ")) {
      queryString = new StringBuilder(queryString.substring(0, queryString.length() - 2));
    }

    queryString.append("} }");

    return new StringQuery(queryString.toString(), pageable);
  }
}