package com.artsykov.backend.repository;

import com.artsykov.backend.entity.WalletEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<WalletEntity, Integer> {
    WalletEntity findByIdWallet (@Param("id_wallet") int id);

    @Query(value = "SELECT * FROM backend.wallet INNER JOIN customer on customer.id_wallet = wallet.id_wallet " +
            "WHERE id_customer = ?1", nativeQuery=true)
    WalletEntity findWalletEntityByIdCustomer(@Param("id_customer") int id);

    @Query(value = "SELECT * FROM backend.wallet INNER JOIN company on company.id_wallet = wallet.id_wallet " +
            "WHERE id_company = ?1", nativeQuery=true)
    WalletEntity findWalletEntityByIdCompany(@Param("id_company") int id);
}
