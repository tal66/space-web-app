package mt.spacewebapp.controllers;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.controllers.shared.DtoUtil;
import mt.spacewebapp.dto.DestinationDto;
import mt.spacewebapp.models.*;
import mt.spacewebapp.services.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@Slf4j
public class DestinationController {

    @Autowired
    private DtoUtil dtoUtil;
    private IDestinationService destinationService;

    public DestinationController(IDestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping({"/", "/home"})
    public String destinationsByType(Model model) {
        Map<String, List<Destination>> destinationsByTypeMap = destinationService.destinationsByTypeMap();
        Map<String, List<DestinationDto>> destinationsDtoByType = destinationsByTypeMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> dtoUtil.mapList(e.getValue(), DestinationDto.class)));
        model.addAttribute("destinationsByTypeMap", destinationsDtoByType);
        return "home";
    }


}
