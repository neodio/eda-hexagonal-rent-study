package hello.rent.application.inputport;


import hello.rent.application.outputport.EventOutputPort;
import hello.rent.application.outputport.RentalCardOutputPort;
import hello.rent.application.usecase.RentItemUsecase;
import hello.rent.domain.model.RentalCard;
import hello.rent.domain.model.vo.IDName;
import hello.rent.framework.web.dto.RentalCardOutputDTO;
import hello.rent.framework.web.dto.UserItemInputDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RentItemInputPort implements RentItemUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalCardOutputDTO rentItem(UserItemInputDTO rental) throws Exception {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(rental.userId)
                .orElseGet(() -> RentalCard.createRentalCard(new IDName(rental.getUserId(), rental.getUserNm())));

        Item newItem = new Item(rental.getItemId(),rental.getItemTitle());
        rentalCard.rentItem(newItem);
        //대여 이벤트 생성 및 발행
        domain.model.event.ItemRented itemRentedEvent = RentalCard.createItemRentedEvent(rentalCard.getMember(), newItem, 10L);
        eventOutputPort.occurRentalEvent(itemRentedEvent);

        return RentalCardOutputDTO.mapToDTO(rentalCard);
    }
}
