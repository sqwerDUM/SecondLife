package com.itacademy.finalprod.project_.repo;

import com.itacademy.finalprod.project_.model.Donation;
import com.itacademy.finalprod.project_.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByUser (User user);
}
