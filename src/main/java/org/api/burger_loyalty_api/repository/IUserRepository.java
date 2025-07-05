package org.api.burger_loyalty_api.repository;

import org.api.burger_loyalty_api.dto.UserDashboardDto;
import org.api.burger_loyalty_api.dto.UserStampsDto;
import org.api.burger_loyalty_api.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

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

    //Optional<UserStampsDto> findClientTarget(@Param("mobileNumber") String mobileNumber);

    Optional<User> findByMobileNumber(String mobileNumber);

    @Query("SELECT u.id FROM User u WHERE u.mobileNumber = :mobileNumber")
    Optional<Long> findIdByMobileNumber(@Param("mobileNumber") String mobileNumber);

    void removeByMobileNumber(String mobileNumber);

    Boolean existsByMobileNumberOrEmail(String mobileNumber, String email);

}
