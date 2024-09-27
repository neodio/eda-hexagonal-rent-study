package hello.rent.application.usecase;


import hello.rent.framework.web.dto.RentalCardOutputDTO;
import hello.rent.framework.web.dto.UserItemInputDTO;

public interface RentItemUsecase {
    public RentalCardOutputDTO rentItem(UserItemInputDTO rental) throws Exception;

}
