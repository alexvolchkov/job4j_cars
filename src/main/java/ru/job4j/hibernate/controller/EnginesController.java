package ru.job4j.hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.hibernate.CommonMethods;
import ru.job4j.hibernate.entity.Engine;
import ru.job4j.hibernate.service.EngineService;

import javax.servlet.http.HttpSession;

@Controller
public class EnginesController {
    private final EngineService engineService;

    public EnginesController(EngineService engineService) {
        this.engineService = engineService;
    }

    @GetMapping("/engines")
    public String engines(Model model, HttpSession session) {
        model.addAttribute("engines", engineService.findAll());
        model.addAttribute("user", CommonMethods.getUserFromSession(session));
        return "engines";
    }

    @GetMapping("/formAddEngine")
    public String formAddEngine(Model model, HttpSession session) {
        model.addAttribute("user", CommonMethods.getUserFromSession(session));
        model.addAttribute("engine", new Engine());
        return "addEngine";
    }

    @GetMapping("/formEngine/{engineId}")
    public String formEngine(Model model, HttpSession session, @PathVariable("engineId") int id) {
        model.addAttribute("engine", engineService.findById(id).get());
        model.addAttribute("user", CommonMethods.getUserFromSession(session));
        return "formEngine";
    }

    @PostMapping("/createEngine")
    public String createEngine(@ModelAttribute("engine") Engine engine) {
        engineService.add(engine);
        return "redirect:/engines";
    }

    @GetMapping("/formEngineUpdate/{engineId}")
    public String formEngineUpdate(Model model, HttpSession session, @PathVariable("engineId") int id) {
        model.addAttribute("engine", engineService.findById(id).get());
        model.addAttribute("user", CommonMethods.getUserFromSession(session));
        return "formEngineUpdate";
    }

    @PostMapping("/updateEngine")
    public String updateEngine(@ModelAttribute("engine") Engine engine) {
        engineService.update(engine.getId(), engine);
        return "redirect:/engines";
    }
}
