package com.invillia.challenge.storeservice.repositories;

import com.invillia.challenge.storeservice.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, JpaSpecificationExecutor<Store> {
}