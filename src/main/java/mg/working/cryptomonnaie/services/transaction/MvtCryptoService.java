package mg.working.cryptomonnaie.services.transaction;

import mg.working.cryptomonnaie.model.transaction.MvtCrypto;
import mg.working.cryptomonnaie.repository.transaction.MvtCryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MvtCryptoService {
    @Autowired
    MvtCryptoRepository mvtCryptoRepository;

    public void insertMvtCrypto(MvtCrypto mvtCrypto) {
        this.mvtCryptoRepository.save(mvtCrypto);
    }

    public List<MvtCrypto> getAllMvtCrypto() {
        return this.mvtCryptoRepository.findAll();
    }
}
