package mg.working.cryptomonnaie.controller.crypto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CryptoMonnaieController {
    @GetMapping("/")
    public String index (){
        return "index";
    }
}
