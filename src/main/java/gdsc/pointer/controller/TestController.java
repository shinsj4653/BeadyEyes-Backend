package gdsc.pointer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @RequestMapping("/")
    public String test() {
        log.info("test");
        return "This is a root path for SpringBoot Backend Local Server ci/cd test 7";
    }

}
