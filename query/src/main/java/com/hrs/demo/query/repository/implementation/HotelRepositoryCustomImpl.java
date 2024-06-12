package com.hrs.demo.query.repository.implementation;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import com.hrs.demo.query.entities.Hotel;
import com.hrs.demo.query.repository.IHotelRepositoryCustom;
import com.hrs.demo.query.web.dto.request.GetAllHotelCriteria;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
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
public class HotelRepositoryCustomImpl implements IHotelRepositoryCustom {

  private final ElasticsearchOperations elasticsearchOperations;


  @Override
  public Page<Hotel> search(GetAllHotelCriteria criteria, Pageable pageable) {
    final SearchHits<Hotel> searchHits = elasticsearchOperations.search(
        buildQuery(criteria, pageable), Hotel.class);
    return PageableExecutionUtils.getPage(searchHits.map(SearchHit::getContent).toList(), pageable,
        searchHits::getTotalHits);
  }

  private Query buildQuery(GetAllHotelCriteria request, Pageable pageable) {
    StringBuilder queryString = new StringBuilder("{ \"bool\": { ");
    final var keyword = request.getKeyword();
    if (StringUtils.isNotBlank(keyword)) {
      queryString.append("\"must\": { ")
          .append("\"multi_match\": { ")
          .append("\"query\": \"").append(keyword).append("\", ")
          .append("\"fields\": [\"name\", \"description\", \"address.text\", \"address.ward\", \"address.district\", \"address.province\"], ")
          .append("\"type\": \"phrase\" ")
          .append("} ")
          .append("} ");
    }
    queryString.append("} }");

    return new StringQuery(queryString.toString(), pageable);

  }
}