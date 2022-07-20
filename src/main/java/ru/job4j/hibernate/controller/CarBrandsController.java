package ru.job4j.hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.hibernate.CommonMethods;
import ru.job4j.hibernate.entity.CarBrand;
import ru.job4j.hibernate.service.CarBrandService;

import javax.servlet.http.HttpSession;

@Controller
public class CarBrandsController {
    private final CarBrandService carBrandService;

    public CarBrandsController(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    @GetMapping("/car_brands")
    public String carBrands(Model model, HttpSession session) {
        model.addAttribute("carBrands", carBrandService.findAll());
        model.addAttribute("user", CommonMethods.getUserFromSession(session));
        return "car_brands";
    }

    @GetMapping("/formAddCarBrand")
    public String formAddCarBrand(Model model, HttpSession session) {
        model.addAttribute("user", CommonMethods.getUserFromSession(session));
        model.addAttribute("carBrand", new CarBrand());
        return "addCarBrand";
    }

    @GetMapping("/formCarBrand/{brandId}")
    public String formCarBrand(Model model, HttpSession session, @PathVariable("brandId") int id) {
        model.addAttribute("carBrand", carBrandService.findById(id).get());
        model.addAttribute("user", CommonMethods.getUserFromSession(session));
        return "formCarBrand";
    }

    @PostMapping("/createCarBrand")
    public String createCarBrand(@ModelAttribute("carBrand") CarBrand carBrand) {
        carBrandService.add(carBrand);
        return "redirect:/car_brands";
    }

    @GetMapping("/formCarBrandUpdate/{carBrandId}")
    public String formCarBrandUpdate(Model model, HttpSession session, @PathVariable("carBrandId") int id) {
        model.addAttribute("carBrand", carBrandService.findById(id).get());
        model.addAttribute("user", CommonMethods.getUserFromSession(session));
        return "formCarBrandUpdate";
    }

    @PostMapping("/updateCarBrand")
    public String updateCarBrand(@ModelAttribute("carBrand") CarBrand carBrand) {
        carBrandService.update(carBrand.getId(), carBrand);
        return "redirect:/car_brands";
    }
}
