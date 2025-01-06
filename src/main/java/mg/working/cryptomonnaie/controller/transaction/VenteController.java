package mg.working.cryptomonnaie.controller.transaction;

import mg.working.cryptomonnaie.model.crypto.CryptoMonnaie;
import mg.working.cryptomonnaie.model.transaction.Portefeuille;
import mg.working.cryptomonnaie.model.user.Utilisateur;
import mg.working.cryptomonnaie.services.crypto.CryptoMonnaieService;
import mg.working.cryptomonnaie.services.transaction.PortefeuilleService;
import mg.working.cryptomonnaie.services.transaction.TransactionCryptoService;
import mg.working.cryptomonnaie.services.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/api/vente")
public class VenteController {
    @Autowired
    CryptoMonnaieService cryptoMonnaieService;
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    TransactionCryptoService transactionCryptoService;
    @Autowired
    PortefeuilleService portefeuilleService;

    @GetMapping("/ventePage")
    public String goToVentePage(Model model) {
        List<CryptoMonnaie> cryptoMonnaies = this.cryptoMonnaieService.getAllCrypto();

        model.addAttribute("cryptoMonnaies", cryptoMonnaies);
        return "transaction/vente";
    }

    @PostMapping("/venteCrypto")
    public String vendreCrypto(@RequestParam("userId") Integer userId,
                               @RequestParam("cryptoId") Integer cryptoId,
                               @RequestParam("quantite") BigDecimal quantite) {
        Utilisateur utilisateur = this.utilisateurService.getUtilisateurById(userId);
        CryptoMonnaie cryptoMonnaie = this.cryptoMonnaieService.getCryptoById(cryptoId);
        if (utilisateur == null || cryptoMonnaie == null) {
            return "Utilisateur ou Cryptomonnaie introuvable";
        }
        BigDecimal valeurTotalVente = cryptoMonnaie.calculTotalVente(quantite);
        double newSoldeUser = utilisateur.calculNewSolde(Double.parseDouble(valeurTotalVente.toString()));
        utilisateur.setSolde(newSoldeUser);

        Portefeuille portefeuille = this.portefeuilleService.getPortefeuilleByCrypto(cryptoMonnaie);
        this.portefeuilleService.updateQuantiteCrypto(portefeuille , quantite);

        this.transactionCryptoService.insertNewTransactionCrypto(cryptoMonnaie , quantite , valeurTotalVente);

        return "Vente valide";
    }
}
