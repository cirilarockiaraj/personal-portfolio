package com.app.personal_portfolio.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.personal_portfolio.entity.AppData;

@Repository
public interface PortfolioRepository extends MongoRepository<AppData, String> {

}
