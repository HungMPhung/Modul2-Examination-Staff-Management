package com.codegym.staffmanagement.service;

import com.codegym.staffmanagement.model.Team;

public interface TeamService {
    Iterable<Team> findAll();

    Team findById(Long id);
}
