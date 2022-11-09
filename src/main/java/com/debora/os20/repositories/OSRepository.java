package com.debora.os20.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.debora.os20.domain.OS;

@Repository
public interface OSRepository extends JpaRepository<OS, Integer> {

}
