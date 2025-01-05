package mg.working.cryptomonnaie.services.transaction;

import mg.working.cryptomonnaie.model.crypto.CryptoMonnaie;
import mg.working.cryptomonnaie.model.transaction.MvtCrypto;
import mg.working.cryptomonnaie.repository.crypto.CryptoMonnaieRepository;
import mg.working.cryptomonnaie.repository.transaction.MvtCryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class MvtCryptoService {
    @Autowired
    MvtCryptoRepository mvtCryptoRepository;

    @Autowired
    private CryptoMonnaieRepository cryptoMonnaieRepository;

    public void insertMvtCrypto(MvtCrypto mvtCrypto) {
        this.mvtCryptoRepository.save(mvtCrypto);
    }

    public List<MvtCrypto> getAllMvtCrypto() {
        return this.mvtCryptoRepository.findAll();
    }

    public List<MvtCrypto> getAllLastMvtCrypto() { return this.mvtCryptoRepository.getAllLastMvtCrypto(); }

    public List<MvtCrypto> get7LastMvtCryptoByCrypto(int id) { return mvtCryptoRepository.get7LastMvtCryptoByCrypto(id); }

    private final Random random = new Random();

    @Scheduled(fixedRate = 10000) // Toutes les 10 secondes
    public void generateRandomVariation() {
        List<CryptoMonnaie> cryptos = cryptoMonnaieRepository.findAll();

        for (CryptoMonnaie crypto : cryptos) {
            BigDecimal variation = BigDecimal.valueOf(-50)
                    .add(BigDecimal.valueOf(150)
                            .multiply(BigDecimal.valueOf(random.nextDouble())));
            System.out.println(crypto.getPrixUnitaire());
            MvtCrypto mvtCrypto = new MvtCrypto();
            mvtCrypto.setCryptoMonnaie(crypto);
            mvtCrypto.setVariation(variation);
            mvtCrypto.setVariationValue(crypto.getPrixUnitaire());
            mvtCrypto.setDateHeure(LocalDateTime.now());

            crypto.setPrixUnitaire(variation);
            cryptoMonnaieRepository.save(crypto);
            mvtCryptoRepository.save(mvtCrypto);

        }
    }

}
