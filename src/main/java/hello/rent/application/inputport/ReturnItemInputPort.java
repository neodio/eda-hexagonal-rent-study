package hello.rent.application.inputport;

import hello.rent.application.outputport.EventOutputPort;
import hello.rent.application.outputport.RentalCardOutputPort;
import hello.rent.application.usecase.ReturnItemUsercase;
import hello.rent.domain.model.RentalCard;
import hello.rent.domain.model.event.ItemReturned;
import hello.rent.domain.model.vo.Item;
import hello.rent.framework.web.dto.RentalCardOutputDTO;
import hello.rent.framework.web.dto.UserItemInputDTO;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ReturnItemInputPort implements ReturnItemUsercase {

    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;
    @Override
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnDto) throws Exception {
        // OutputPort를 사용해서 rentalCard 검색한 후 없으면 Exception처리, 있으면 도서 반납
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(returnDto.userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));

        Item retrunItem = new Item(returnDto.getItemId(),returnDto.getItemTitle());
        rentalCard.returnItem(retrunItem, LocalDate.now());
        //이벤트 생성 발행
        ItemReturned itemReturnEvent = RentalCard.createItemReturnEvent(rentalCard.getMember(), retrunItem, 10L);
        eventOutputPort.occurReturnEvent(itemReturnEvent);

        return RentalCardOutputDTO.mapToDTO(rentalCard);
    }
}
