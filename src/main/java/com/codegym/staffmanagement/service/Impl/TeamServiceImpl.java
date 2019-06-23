package com.codegym.staffmanagement.service.Impl;

import com.codegym.staffmanagement.model.Team;
import com.codegym.staffmanagement.repository.TeamRepository;
import com.codegym.staffmanagement.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Override
    public Iterable<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team findById(Long id) {
        return teamRepository.findById(id).get();
    }
}
