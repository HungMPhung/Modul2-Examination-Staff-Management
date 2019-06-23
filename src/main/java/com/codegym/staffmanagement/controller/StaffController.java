package com.codegym.staffmanagement.controller;

import com.codegym.staffmanagement.model.Staff;
import com.codegym.staffmanagement.model.Team;
import com.codegym.staffmanagement.service.StaffService;
import com.codegym.staffmanagement.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
public class StaffController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private TeamService teamService;

    @ModelAttribute("teams")
    public Iterable<Team> teams() {
        return teamService.findAll();
    }

    @GetMapping("/list")
    public ModelAndView showList(@RequestParam("findAllInOne") Optional<String> findAllInOne, @PageableDefault(value = 5) Pageable pageable) {
        Page<Staff> staffs;
        ModelAndView modelAndView = new ModelAndView("staff/list");

        if (findAllInOne.isPresent()) {
            staffs = staffService.findAllByNameContainingOrStaffcodeContaining(findAllInOne.get(), findAllInOne.get(), pageable);
        } else {
            staffs = staffService.findAll(pageable);
        }
        modelAndView.addObject("staff", staffs);
        return modelAndView;
    }

    @GetMapping("/create-staff")
    public ModelAndView createFrom(@Validated @ModelAttribute("staff") Staff staff, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("staff/create");
            return modelAndView;
        } else {
            staffService.save(staff);
            ModelAndView modelAndView = new ModelAndView("staff/create");
            modelAndView.addObject("staff", new Staff());
            modelAndView.addObject("message", "Information added successful!");
            return modelAndView;
        }
    }

    @PostMapping("/create-staff")
    public ModelAndView creatForm(@Validated @ModelAttribute("staff") Staff staff, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("staff/create");
            return modelAndView;
        }
        staffService.save(staff);
        ModelAndView modelAndView = new ModelAndView("staff/create");
        modelAndView.addObject("staff", new Staff());
        modelAndView.addObject("message", "New Staff Created!");
        return modelAndView;
    }

    @GetMapping("/staff-edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        Staff staff = staffService.findById(id);
        ModelAndView modelAndView = new ModelAndView("staff/edit");
        modelAndView.addObject("staff", staff);
        return modelAndView;
    }

    @PostMapping("/staff-edit")
    public ModelAndView editForm(@Validated @ModelAttribute("staff") Staff staff, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("staff/edit");
            return modelAndView;
        }
        staffService.save(staff);
        ModelAndView modelAndView = new ModelAndView("staff/edit");
        modelAndView.addObject("message", "The information is updated!");
        return modelAndView;
    }

    @GetMapping("/staff-delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        staffService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/list");
        redirectAttributes.addFlashAttribute("message", "the staff is deleted!" );
        return modelAndView;
    }
}
