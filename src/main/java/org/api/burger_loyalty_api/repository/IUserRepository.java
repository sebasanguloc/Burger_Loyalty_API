package org.api.burger_loyalty_api.repository;

import org.api.burger_loyalty_api.dto.ActiveStampDto;
import org.api.burger_loyalty_api.dto.TotalStampDto;
import org.api.burger_loyalty_api.dto.UserDashboardDto;
import org.api.burger_loyalty_api.dto.UserTargetDto;
import org.api.burger_loyalty_api.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("""
        SELECT new org.api.burger_loyalty_api.dto.UserDashboardDto(
            u.name, u.mobileNumber, COUNT(ts.user)
        )
        FROM User u
        LEFT JOIN u.totalStamps ts
        WHERE NOT EXISTS (
            SELECT 1 FROM Authority au
            WHERE au.user = u AND au.name = 'ROLE_ADMIN'
            )
        GROUP BY u.name, u.mobileNumber
    """)
    Page<UserDashboardDto> findAllClients(Pageable pageable);

    /*
    select u.name, u.mobile_number, COUNT(ts.id) as total_stamps, COUNT(acs.id) as total_active_stamps from users u
    left join total_stamps ts
    on u.id  = ts.user_id
    left join active_stamps acs
    on u.id = acs.user_id
    where u.mobile_number = '3196611933'
    group by u.name, u.mobile_number;
     */

    @Query("""
       SELECT new org.api.burger_loyalty_api.dto.UserTargetDto(
            u.name,
            u.mobileNumber,
            COUNT(ts.id),
            null,
            COUNT(acs.id),
            null
       )
       FROM User u
       LEFT JOIN u.totalStamps ts
       LEFT JOIN u.activeStamps acs
       WHERE u.mobileNumber = :mobileNumber
       GROUP BY u.name, u.mobileNumber
    """)
    UserTargetDto findClientTotalStampsActiveStamps(@Param("mobileNumber") String mobileNumber);

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

    Optional<User> findByMobileNumber(String mobileNumber);

    @Query("SELECT u.id FROM User u WHERE u.mobileNumber = :mobileNumber")
    Optional<Long> findIdByMobileNumber(@Param("mobileNumber") String mobileNumber);

    void removeByMobileNumber(String mobileNumber);

    Boolean existsByMobileNumberOrEmail(String mobileNumber, String email);

}
