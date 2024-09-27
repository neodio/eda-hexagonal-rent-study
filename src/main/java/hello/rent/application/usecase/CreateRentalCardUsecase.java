package hello.rent.application.usecase;


import hello.rent.framework.web.dto.RentalCardOutputDTO;
import hello.rent.framework.web.dto.UserInputDTO;

public interface CreateRentalCardUsecase {
    public RentalCardOutputDTO createRentalCard(UserInputDTO userInputDTO);
}
