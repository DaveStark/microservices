package com.dave.microservices.metrics.repository;

import com.dave.microservices.metrics.domain.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by davestark on 12/26/16.
 */
public interface AccountDetailRepository extends JpaRepository<AccountDetail,Integer>{
}
