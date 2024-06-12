package com.hrs.demo.query.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

//
//public class ElasticSearchConfiguration extends ElasticsearchConfiguration {
//
//  @Override
//  public ClientConfiguration clientConfiguration() {
//    return null;
//  }
//
////  @Override
////  public ClientConfiguration clientConfiguration() {
////    return ClientConfiguration.builder()
////        .connectedTo("localhost:9200")
////        .build();
////  }
//
//}
