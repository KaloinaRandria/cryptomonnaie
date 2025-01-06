package mg.working.cryptomonnaie.controller.transaction;

import mg.working.cryptomonnaie.model.crypto.CryptoMonnaie;
import mg.working.cryptomonnaie.services.crypto.CryptoMonnaieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/vente")
public class VenteController {
    @Autowired
    CryptoMonnaieService cryptoMonnaieService;

    public String goToVentePage(Model model) {
        List<CryptoMonnaie> cryptoMonnaies = this.cryptoMonnaieService.getAllCrypto();

        model.addAttribute("cryptoMonnaies", cryptoMonnaies);
        return "transaction/vente";
    }
}
