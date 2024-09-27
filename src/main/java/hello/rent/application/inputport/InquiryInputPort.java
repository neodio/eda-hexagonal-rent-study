package hello.rent.application.inputport;

import hello.rent.application.outputport.RentalCardOutputPort;
import hello.rent.application.usecase.InquiryUsecase;
import hello.rent.framework.web.dto.RentItemOutputDTO;
import hello.rent.framework.web.dto.RentalCardOutputDTO;
import hello.rent.framework.web.dto.ReturnItemOutputDTO;
import hello.rent.framework.web.dto.UserInputDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryInputPort implements InquiryUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;
    @Override
    public Optional<RentalCardOutputDTO> getRentalCard(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.UserId)
                .map(RentalCardOutputDTO::mapToDTO);
    }

    @Override
    public Optional<List<RentItemOutputDTO>> getAllRentItem(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.UserId)
                .map(loadCard -> loadCard.getRentalItemList()
                        .stream()
                        .map(RentItemOutputDTO::mapToDTO)
                        .collect(Collectors.toList()));
    }

    @Override
    public Optional<List<ReturnItemOutputDTO>> getAllReturnItem(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.UserId)
                .map(loadCard -> loadCard.getReturnItemLIst()
                        .stream()
                        .map(ReturnItemOutputDTO::mapToDTO)
                        .collect(Collectors.toList()));
    }
}
