package org.api.burger_loyalty_api.repository;

import org.api.burger_loyalty_api.model.ActiveStamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActiveStampRepository extends JpaRepository<ActiveStamp,Long> {
}
