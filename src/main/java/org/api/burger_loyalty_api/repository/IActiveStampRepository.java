package org.api.burger_loyalty_api.repository;

import org.api.burger_loyalty_api.dto.ActiveStampDto;
import org.api.burger_loyalty_api.model.ActiveStamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IActiveStampRepository extends JpaRepository<ActiveStamp,Long> {

    @Modifying
    @Query("DELETE FROM ActiveStamp acs WHERE acs.id IN :ids")
    void removeActiveStampsByIds(@Param("ids") List<Long> ids);

    /*
    select acs.id, acs.enable_dt, acs.expiration_dt from active_stamps acs
    left join users u
    on acs.user_id = u.id
    where u.mobile_number = '3196611933'
     */

    @Query("""
        SELECT new org.api.burger_loyalty_api.dto.ActiveStampDto(
            acs.id,
            acs.enableDt,
            acs.expirationDt
        )
        FROM ActiveStamp acs
        LEFT JOIN acs.user u
        WHERE u.mobileNumber = :mobileNumber
    """)
    List<ActiveStampDto> findAllActiveStampsByMobileNumber(@Param("mobileNumber") String mobileNumber);

}
