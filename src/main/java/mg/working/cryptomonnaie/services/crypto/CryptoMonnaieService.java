package mg.working.cryptomonnaie.services.crypto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.working.cryptomonnaie.model.crypto.CryptoMonnaie;
import mg.working.cryptomonnaie.repository.crypto.CryptoMonnaieRepository;

@Service
public class CryptoMonnaieService {
    @Autowired
    CryptoMonnaieRepository cryptoMonnaieRepository;

    public void insertCrypto(CryptoMonnaie cryptoMonnaie) {
        this.cryptoMonnaieRepository.save(cryptoMonnaie);
    }

    public List<CryptoMonnaie> getAllCrypto() {
        return this.cryptoMonnaieRepository.findAll();
    }
}
