package com.codegym.staffmanagement.repository;

import com.codegym.staffmanagement.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StaffRepository extends PagingAndSortingRepository<Staff, Long> {
    Page<Staff>findAllByNameContainingOrStaffcodeContaining(String name, String staffcode, Pageable pageable);
}
