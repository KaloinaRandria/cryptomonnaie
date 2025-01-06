package mg.working.cryptomonnaie.services.transaction;

import mg.working.cryptomonnaie.model.transaction.TransactionCrypto;
import mg.working.cryptomonnaie.repository.transaction.TransactionCryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
