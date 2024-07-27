package com.example.factory.Repository;

import com.example.factory.Entity.Sale.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, UUID> {
    @Query("""
            select (count(e) > 0) from Expenses e
            where e.CostOfAdvertisement = ?1 and e.Description = ?2 and e.TypeOfAdvertisement = ?3""")
    boolean existsByCostOfAdvertisementAndDescriptionAndTypeOfAdvertisement(Double costOfAdvertisement, String description, String typeOfAdvertisement);
}
