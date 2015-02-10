package com.ogomonkey.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerGatewayController {

    @RequestMapping("/welcome")
    public ModelAndView greetCustomer() {
        String message = "<br><div align='center'>"
            + "<h3>********** Shoutout!!! Ofudii demo system</h3>This message is comming from OgoMonkey Software Inc **********<br><br>";
        return new ModelAndView("welcome", "message", message);
    }
}
