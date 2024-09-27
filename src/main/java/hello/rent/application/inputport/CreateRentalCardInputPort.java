package hello.rent.application.inputport;

import hello.rent.application.outputport.RentalCardOutputPort;
import hello.rent.application.usecase.CreateRentalCardUsecase;
import hello.rent.domain.model.RentalCard;
import hello.rent.domain.model.vo.IDName;
import hello.rent.framework.web.dto.RentalCardOutputDTO;
import hello.rent.framework.web.dto.UserInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateRentalCardInputPort implements CreateRentalCardUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO createRentalCard(UserInputDTO owner) {
        RentalCard rentalCard = RentalCard.createRentalCard(new IDName(owner.getUserId(), owner.getUserNm()));
        RentalCard save = rentalCardOutputPort.save(rentalCard);
        return RentalCardOutputDTO.mapToDTO(save);

    }
}
