package mg.working.cryptomonnaie.services.transaction;

import mg.working.cryptomonnaie.model.crypto.CryptoMonnaie;
import mg.working.cryptomonnaie.model.transaction.TransactionCrypto;
import mg.working.cryptomonnaie.model.user.Utilisateur;
import mg.working.cryptomonnaie.repository.transaction.TransactionCryptoRepository;
import mg.working.cryptomonnaie.services.utilisateur.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionCryptoService {
    @Autowired
    TransactionCryptoRepository transactionCryptoRepository;

    @Autowired
    private UtilisateurService utilisateurService;  // Injecter UtilisateurService

    public void insertTransactionCrypto(TransactionCrypto transactionCrypto) {
        this.transactionCryptoRepository.save(transactionCrypto);
    }

    public List<TransactionCrypto> getAllTransactionCrypto() {
        return this.transactionCryptoRepository.findAll();
    }

    public boolean estSuffisantSolde(int utilisateurId, BigDecimal montantTotal) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(utilisateurId);  // Récupérer l'utilisateur
        if (utilisateur == null) {
            throw new IllegalArgumentException("Utilisateur non trouvé");
        }
        
        // Convertir le solde de l'utilisateur en BigDecimal
        BigDecimal soldeUtilisateur = BigDecimal.valueOf(utilisateur.getSolde()).setScale(2, RoundingMode.HALF_UP);

        // Comparer le solde de l'utilisateur avec le montant total
        return soldeUtilisateur.compareTo(montantTotal) >= 0;
    }

    public void insertNewTransactionCrypto(CryptoMonnaie cryptoMonnaie , BigDecimal quantiteAVendre , BigDecimal valeurTotalVente) {
        TransactionCrypto transactionCrypto = new TransactionCrypto();
        transactionCrypto.setCryptoMonnaie(cryptoMonnaie);
        transactionCrypto.setQuantite(quantiteAVendre);
        transactionCrypto.setPrixTotal(valeurTotalVente);
        transactionCrypto.setDateHeure(LocalDateTime.now());
        transactionCrypto.setTypeTransaction(TransactionCrypto.TypeTransaction.VENTE);

        this.insertTransactionCrypto(transactionCrypto);
    }
}
