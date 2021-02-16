package com.mehrdad.reviewproduct.repository;

import com.mehrdad.reviewproduct.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by m.peykari on 2/15/2021.
 */
@Repository
public interface ProviderRepository extends JpaRepository<Provider,Long> {

}