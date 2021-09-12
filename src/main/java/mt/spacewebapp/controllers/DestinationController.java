package mt.spacewebapp.controllers;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.models.*;
import mt.spacewebapp.services.DestinationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
@Slf4j
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/")
    public String destinationsByType(Model model) {
        Map<String, List<Destination>> destinationsByTypeMap = destinationService.DestinationsByTypeMap();
        model.addAttribute("destinationsByTypeMap", destinationsByTypeMap);
        return "home";
    }


}
