package mg.working.cryptomonnaie.controller.vente;

import mg.working.cryptomonnaie.model.transaction.Portefeuille;
import mg.working.cryptomonnaie.services.transaction.PortefeuilleService;
import mg.working.cryptomonnaie.services.utilisateur.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vente")
public class VenteController {
    @Autowired
    PortefeuilleService portefeuilleService;
    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping("portefeuille/{idUtilisateur}")
    public List<Portefeuille> getUserPortefeuille(int idUtilisateur) {
        return this.portefeuilleService.getPortefeuilleByUser(
                this.utilisateurService.getUtilisateurById(idUtilisateur));
    }
}
