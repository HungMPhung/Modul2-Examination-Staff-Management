package com.codegym.staffmanagement.service.Impl;

import com.codegym.staffmanagement.model.Staff;
import com.codegym.staffmanagement.repository.StaffRepository;
import com.codegym.staffmanagement.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffRepository staffRepository;

    @Override
    public Page<Staff> findAll(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }

    @Override
    public Staff findById(Long id) {
        return staffRepository.findById(id).get();
    }

    @Override
    public void save(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void remove(Long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public Page<Staff> findAllByNameContainingOrStaffcodeContaining(String name, String staffcode, Pageable pageable) {
        return staffRepository.findAllByNameContainingOrStaffcodeContaining(name, staffcode, pageable);
    }
}
