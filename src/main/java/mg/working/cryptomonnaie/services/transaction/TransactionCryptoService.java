package mg.working.cryptomonnaie.services.transaction;

import mg.working.cryptomonnaie.model.crypto.CryptoMonnaie;
import mg.working.cryptomonnaie.model.transaction.TransactionCrypto;
import mg.working.cryptomonnaie.repository.transaction.TransactionCryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionCryptoService {
    @Autowired
    TransactionCryptoRepository transactionCryptoRepository;

    public void insertTransactionCrypto(TransactionCrypto transactionCrypto) {
        this.transactionCryptoRepository.save(transactionCrypto);
    }

    public List<TransactionCrypto> getAllTransactionCrypto() {
        return this.transactionCryptoRepository.findAll();
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
