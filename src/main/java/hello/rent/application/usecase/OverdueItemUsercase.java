package hello.rent.application.usecase;


import hello.rent.framework.web.dto.RentalCardOutputDTO;
import hello.rent.framework.web.dto.UserItemInputDTO;

public interface OverdueItemUsercase {
    public RentalCardOutputDTO overDueItem(UserItemInputDTO rental) throws Exception;
}
