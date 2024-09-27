package hello.rent.domain.model;

import hello.rent.domain.model.vo.Item;
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
public class RentalItem {
   @Embedded
   private Item item;
   private LocalDate rentDate;

   private boolean overdued;

   private LocalDate overdueDate; //반납예정일

   public static RentalItem createRentalItem(Item item)
   {
    return new RentalItem(item,LocalDate.now(),false,LocalDate.now().plusDays(14));
   }

   public static RentalItem sample(){
    return RentalItem.createRentalItem(Item.Sample());
   }
}
