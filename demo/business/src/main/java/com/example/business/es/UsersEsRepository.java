package com.example.business.es;

import com.example.business.entity.Users;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author 10696
 */

@Component
public interface UsersEsRepository extends ElasticsearchRepository<Users, Integer> {

}
