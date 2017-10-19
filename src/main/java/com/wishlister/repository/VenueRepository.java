package com.wishlister.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wishlister.model.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long>{

}
