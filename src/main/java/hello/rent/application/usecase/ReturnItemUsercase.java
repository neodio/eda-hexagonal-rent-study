package hello.rent.application.usecase;


import hello.rent.framework.web.dto.RentalCardOutputDTO;
import hello.rent.framework.web.dto.UserItemInputDTO;

public interface ReturnItemUsercase {
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnDto) throws Exception;
}
