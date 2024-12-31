package mg.working.cryptomonnaie.repository.transaction;

import mg.working.cryptomonnaie.model.transaction.Portefeuille;
import mg.working.cryptomonnaie.model.user.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PortefeuilleRepository extends JpaRepository<Portefeuille, Integer> {
    @Query("select p from Portefeuille p where p.utilisateur = :utilisateur")
    Portefeuille findByUser(Utilisateur utilisateur);
}
