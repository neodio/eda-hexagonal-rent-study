package hello.rent.application.usecase;


import hello.rent.framework.web.dto.RentItemOutputDTO;
import hello.rent.framework.web.dto.RentalCardOutputDTO;
import hello.rent.framework.web.dto.ReturnItemOutputDTO;
import hello.rent.framework.web.dto.UserInputDTO;
import java.util.List;
import java.util.Optional;

public interface InquiryUsecase {
    public Optional<RentalCardOutputDTO> getRentalCard(UserInputDTO userInputDTO);
    public Optional<List<RentItemOutputDTO>> getAllRentItem(UserInputDTO userInputDTO);
    public Optional<List<ReturnItemOutputDTO>> getAllReturnItem(UserInputDTO userInputDTO);
}
