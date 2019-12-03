package com.artsykov.backend.repository;

import com.artsykov.backend.entity.WalletEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<WalletEntity, Integer> {
    WalletEntity findByIdWallet (@Param("id_wallet") int id);
}
