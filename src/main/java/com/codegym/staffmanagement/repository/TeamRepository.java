package com.codegym.staffmanagement.repository;

import com.codegym.staffmanagement.model.Team;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {
}
