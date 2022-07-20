package ru.job4j.hibernate.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.hibernate.CommonMethods;
import ru.job4j.hibernate.entity.*;
import ru.job4j.hibernate.service.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AdController {
    private final AdService adService;
    private final CarBrandService carBrandService;
    private final EngineService engineService;
    private final PhotoService photoService;

    public AdController(AdService adService, CarBrandService carBrandService, EngineService engineService, PhotoService photoService) {
        this.adService = adService;
        this.carBrandService = carBrandService;
        this.engineService = engineService;
        this.photoService = photoService;
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("ads", adService.findAll());
        model.addAttribute("user", CommonMethods.getUserFromSession(session));
        return "index";
    }

    @GetMapping("/formAddAd")//+
    public String formAddAd(Model model, HttpSession session) {
        model.addAttribute("ads", new Advertisement());
        model.addAttribute("carBrands", carBrandService.findAll());
        model.addAttribute("engines", engineService.findAll());
        model.addAttribute("carBodies", CarBody.values());
        model.addAttribute("user", CommonMethods.getUserFromSession(session));
        return "addAd";
    }

    @PostMapping("/createAd")
    public String createAd(HttpSession session,
                           @ModelAttribute("ads") Advertisement ad,
                           @RequestParam("carBrandId") int carBrandId,
                           @RequestParam("carBody") CarBody carBody,
                           @RequestParam("engineId") int engineId,
                           @RequestParam("car_name") String carName,
                           @RequestParam(value = "files") MultipartFile[] files
                           ) throws IOException {
        User user = CommonMethods.getUserFromSession(session);
        CarBrand carBrand = carBrandService.findById(carBrandId).orElse(new CarBrand());
        Engine engine = engineService.findById(engineId).orElse(new Engine());
        Car car = Car.of(carName, engine, carBody, carBrand);
        ad.setCar(car);
        ad.setUser(user);
        adService.add(ad);
        for (MultipartFile file : files) {
            Photo photo = Photo.of(file.getBytes(), ad);
            photoService.add(photo);
        }
        return "redirect:/index";
    }

    @GetMapping("/formAd/{adId}")
    public String formAd(Model model, HttpSession session, @PathVariable("adId") int id) {
        model.addAttribute("ad", adService.findById(id).orElse(new Advertisement()));
        model.addAttribute("user", CommonMethods.getUserFromSession(session));
        return "formAd";
    }

    @GetMapping("/photoCar/{photoId}")
    public ResponseEntity<Resource> download(@PathVariable("photoId") Integer photoId) {
        Photo photo = photoService.findById(photoId).orElse(new Photo());
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(photo.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(photo.getPhoto()));
    }

    @GetMapping("/sold/{adId}")
    public String sold(Model model, HttpSession session, @PathVariable("adId") int id) {
        adService.sold(id);
        return "redirect:/index";
    }

    @GetMapping("/formUpdate/{adId}")
    public String formUpdate(Model model, HttpSession session, @PathVariable("adId") int id) {
        Advertisement ad = adService.findById(id).orElse(new Advertisement());
        model.addAttribute("ad", ad);
        model.addAttribute("user", CommonMethods.getUserFromSession(session));
        model.addAttribute("car", ad.getCar());
        model.addAttribute("carBrands", carBrandService.findAll());
        model.addAttribute("engines", engineService.findAll());
        model.addAttribute("carBodies", CarBody.values());

        return "formAdUpdate";
    }

    @PostMapping("/updateAd")
    public String updateAd(HttpSession session,
                           @ModelAttribute("ad") Advertisement ad,
                           @RequestParam("carBrandId") int carBrandId,
                           @RequestParam("carBody") CarBody carBody,
                           @RequestParam("engineId") int engineId) {
        User user = CommonMethods.getUserFromSession(session);
        Car car = ad.getCar();
        car.setCarBrand(carBrandService.findById(carBrandId).orElse(new CarBrand()));
        car.setEngine(engineService.findById(engineId).orElse(new Engine()));
        car.setCarBody(carBody);
        adService.update(ad);
        return "redirect:/index";
    }
}
