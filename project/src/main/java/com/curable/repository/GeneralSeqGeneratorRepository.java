package com.curable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curable.domain.GeneralSeqGenerator;

public interface GeneralSeqGeneratorRepository extends JpaRepository<GeneralSeqGenerator, Long>{

}

