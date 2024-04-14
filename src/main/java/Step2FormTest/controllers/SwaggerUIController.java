package Step2FormTest.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Hidden
public class SwaggerUIController {

    @GetMapping(value = "/swagger")
    public String swaggerUi() {
        return "redirect:/swagger-ui/index.html?url=/v3/api-docs";
    }
}
