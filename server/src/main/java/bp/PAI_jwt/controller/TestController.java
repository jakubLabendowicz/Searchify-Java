package bp.PAI_jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @RequestMapping({ "/hello" })
    public String welcomePage() {
        return "Welcome!";
    }
}
