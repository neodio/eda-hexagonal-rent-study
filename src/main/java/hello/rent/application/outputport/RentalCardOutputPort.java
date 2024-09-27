package hello.rent.application.outputport;

import hello.rent.domain.model.RentalCard;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalCardOutputPort {
    Optional<RentalCard> loadRentalCard(String userId);
    RentalCard save(RentalCard rentalCard);
}
