package hello.rent.application.inputport;


import hello.rent.application.outputport.EventOutputPort;
import hello.rent.application.outputport.RentalCardOutputPort;
import hello.rent.application.usecase.ClearOverdueItemUsecase;
import hello.rent.domain.model.RentalCard;
import hello.rent.framework.web.dto.ClearOverdueInfoDTO;
import hello.rent.framework.web.dto.RentalResultOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ClearOverdueItemInputPort implements ClearOverdueItemUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO) throws Exception {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(clearOverdueInfoDTO.UserId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));

        rentalCard.makeAvailableRental(clearOverdueInfoDTO.getPoint());

        eventOutputPort.occurOverdueClearedEvent(RentalCard.createOverdueCleardEvent(rentalCard.getMember(),clearOverdueInfoDTO.getPoint()));

        return RentalResultOutputDTO.mapToDTO(rentalCard);
    }
}
