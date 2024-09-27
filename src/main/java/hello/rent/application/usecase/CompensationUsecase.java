package hello.rent.application.usecase;

import hello.rent.domain.model.vo.Item;
import hello.rent.domain.model.RentalCard;
import hello.rent.domain.model.vo.IDName;

public interface CompensationUsecase {

    public RentalCard cancleRentItem(IDName idName, Item item);
    public RentalCard cancleReturnItem(IDName idName,Item item, long point) throws Exception;
    public long cancleMakeAvailableRental(IDName idName, long point);


}
