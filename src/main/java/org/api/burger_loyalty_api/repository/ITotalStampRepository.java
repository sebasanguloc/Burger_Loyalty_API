package org.api.burger_loyalty_api.repository;

import org.api.burger_loyalty_api.dto.TotalStampDto;
import org.api.burger_loyalty_api.model.TotalStamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITotalStampRepository extends JpaRepository<TotalStamp, Long> {

    @Modifying
    @Query("DELETE FROM TotalStamp ts WHERE ts.id NOT IN :ids")
    void removeStampsByIds(@Param("ids") List<Long> ids);

        /*
    select ts.id, ts.stamp_dt from total_stamps ts
    left join users u
    on ts.user_id = u.id
    where u.mobile_number = '1234567890'
     */

    @Query("""
        SELECT  new org.api.burger_loyalty_api.dto.TotalStampDto(
            ts.id,
            ts.stampDt
        )
        FROM TotalStamp ts
        LEFT JOIN ts.user u
        WHERE u.mobileNumber = :mobileNumber
    """)
    List<TotalStampDto> findAllStampsByMobileNumber(@Param("mobileNumber") String mobileNumber);


}
