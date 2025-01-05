package mg.working.cryptomonnaie.services.transaction;

import mg.working.cryptomonnaie.model.transaction.Portefeuille;
import mg.working.cryptomonnaie.model.user.Utilisateur;
import mg.working.cryptomonnaie.repository.transaction.PortefeuilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PortefeuilleService {
    @Autowired
    PortefeuilleRepository portefeuilleRepository;

    public void insertPortefeuille(Portefeuille portefeuille) {
        this.portefeuilleRepository.save(portefeuille);
    }

    public List<Portefeuille> getAllPortefeuille() {
        return this.portefeuilleRepository.findAll();
    }

    public Portefeuille getPortefeuilleByUser(Utilisateur utilisateur) {
        return this.portefeuilleRepository.findByUser(utilisateur);
    }

    public void updateQuantiteCrypto(Portefeuille portefeuille , BigDecimal quantiteAVendre) {
        portefeuille.setQuantite(portefeuille.getQuantite().subtract(quantiteAVendre));
        this.insertPortefeuille(portefeuille);
    }
}
