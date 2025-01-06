package mg.working.cryptomonnaie.repository.transaction;

import mg.working.cryptomonnaie.model.transaction.Portefeuille;
import mg.working.cryptomonnaie.model.user.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortefeuilleRepository extends JpaRepository<Portefeuille, Integer> {
    @Query("select p from Portefeuille p where p.utilisateur = :utilisateur")
    List<Portefeuille> findByUser(Utilisateur utilisateur);
}
