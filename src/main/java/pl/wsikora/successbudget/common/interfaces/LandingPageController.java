package pl.wsikora.successbudget.common.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
class LandingPageController {

    private static final String VIEW = "landing/landing-page";

    @GetMapping
    private String goToView() {

        return VIEW;
    }
}
