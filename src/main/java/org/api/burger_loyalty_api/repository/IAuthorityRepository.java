package org.api.burger_loyalty_api.repository;

import org.api.burger_loyalty_api.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorityRepository extends JpaRepository<Authority,Long> {
}
