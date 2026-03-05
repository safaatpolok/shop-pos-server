package com.shop.Repositorty;

import com.shop.domain.UserRole;
import com.shop.modal.Branch;
import com.shop.modal.Store;
import com.shop.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    List<User> findByStore(Store store);
    List<User> findByBranchId(Long branchId);

    @Query("""
            select count(u)
            from User u
            where u.id in (
                select s.storeAdmin.id
                from Store s
                where s.storeAdmin.id = :storeAdminId
            )
            and u.role in (:roles)
            """)
    int countByStoreAdminIdAndRoles(
            @Param("storeAdminId") Long storeAdminId,
            @Param("roles") List<UserRole> roles
    );

    @Query("""
            select u.fullName
            from User u
            where u.lastLogin < :cutoffDate
            and u.id in (
                 select s.storeAdmin.id
                 from Store s
                 where s.storeAdmin.id = :storeAdminId
            )
            and u.role = com.shop.domain.UserRole.ROLE_BRANCH_CASHIER
            """)
    List<String> findInactiveCashiers(
            @Param("storeAdminId") Long storeAdminId,
            @Param("cutoffDate") LocalDateTime cutoffDate
    );

}