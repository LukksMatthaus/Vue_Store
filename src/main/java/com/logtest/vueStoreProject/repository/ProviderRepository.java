package com.logtest.vueStoreProject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.logtest.vueStoreProject.Model.Provider;


@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

}
