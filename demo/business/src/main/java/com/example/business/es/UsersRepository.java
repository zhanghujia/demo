package com.example.business.es;

import com.example.business.entity.Users;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author 10696
 */

@Repository
public interface UsersRepository extends ElasticsearchRepository<Users, Integer> {

}
