package mg.working.cryptomonnaie.services.transaction;

import mg.working.cryptomonnaie.model.crypto.CryptoMonnaie;
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

    // Récupérer un portefeuille spécifique pour un utilisateur et une cryptomonnaie
    public Portefeuille getPortefeuille(Utilisateur utilisateur, CryptoMonnaie cryptoMonnaie) {
        return portefeuilleRepository.findByUtilisateurAndCryptoMonnaie(utilisateur, cryptoMonnaie).orElse(null);
    }

    // Ajouter une quantité de cryptomonnaie au portefeuille
    public void ajouterQuantite(Utilisateur utilisateur, CryptoMonnaie cryptoMonnaie, BigDecimal quantiteAchete) {
        Portefeuille portefeuille = getPortefeuille(utilisateur, cryptoMonnaie);

        if (portefeuille != null) {
            // Augmenter la quantité existante
            portefeuille.setQuantite(portefeuille.getQuantite().add(quantiteAchete));
        } else {
            // Créer une nouvelle entrée dans le portefeuille
            portefeuille = new Portefeuille();
            portefeuille.setUtilisateur(utilisateur);
            portefeuille.setCryptoMonnaie(cryptoMonnaie);
            portefeuille.setQuantite(quantiteAchete);
        }

        portefeuilleRepository.save(portefeuille);
    }

    public void updateQuantiteCrypto(Portefeuille portefeuille ,BigDecimal quantiteAVendre) {
        portefeuille.setQuantite(portefeuille.getQuantite().add(quantiteAVendre));
        this.insertPortefeuille(portefeuille);
    }
}
