package mt.spacewebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataController {

    @GetMapping("/data")
    public String api_data(){
        return "space_api_data";
    }
}
