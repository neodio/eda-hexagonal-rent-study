package hello.rent.domain.model.vo;

import hello.rent.domain.model.RentalItem;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReturnItem {
    @Embedded
    private RentalItem rentalItem;
    private LocalDate returnDate;

    public static ReturnItem createReturnItem(RentalItem rentalItem){
        return new ReturnItem(rentalItem,LocalDate.now());
    }

    public static ReturnItem sample(){
        return ReturnItem.createReturnItem(RentalItem.sample());
    }
}
