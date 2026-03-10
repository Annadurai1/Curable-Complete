package com.curable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.curable.domain.MenuMaster;

@Repository
public interface MenuMasterRepository extends JpaRepository<MenuMaster, Long>, QuerydslPredicateExecutor<MenuMaster>{

}
