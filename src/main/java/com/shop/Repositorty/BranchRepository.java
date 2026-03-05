package com.shop.Repositorty;

import com.shop.modal.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch,Long> {

    List<Branch> findByStoreId(Long storeId);


    @Query("""
            select count(b) from Branch b
            where b.store.storeAdmin.id=:storeAdminId
            
            """)
    int countByStoreAdinId(
            @Param("storeAdminId") Long storeAdminId

    );

    @Query(
            """
           select count(b)from Branch b
           where b.store.storeAdmin.id=:storeAdminId
           and MONTH(b.createdAt)=MONTH(CURRENT_DATE)
           """
    )

    int countNewBranchesThisMonth(
            @Param("storeAdminId") Long storeAdminId
    );


    @Query(
            """
                    select b.name
                    from Branch b
                    join Order o on o.branch.id=b.id
                    where b.store.storeAdmin.id=:storeAdminId
                    GROUP BY b.id,b.name
                    order by sum(o.totalAmount)desc
                    
                    """
    )


    List<String>findTopBranchBySales(
            @Param("storeAdminId") Long storeAdminId
    );

    @Query(
            """
               select b.name from Branch b
               where b.store.storeAdmin.id=:storeAdminId
               AND b.id not in(
               select distinct o.branch.id
               FROM Order o
               Where DATE(o.createdAt)=CURRENT_DATE
               )
            """)


    List<String> findBranchesWithNoSalesToday(
            @Param("storeAdminId")Long storeAdminId

    );

}
