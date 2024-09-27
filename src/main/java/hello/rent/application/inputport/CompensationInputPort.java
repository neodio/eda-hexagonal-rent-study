package hello.rent.application.inputport;

import hello.rent.application.outputport.EventOutputPort;
import hello.rent.application.outputport.RentalCardOutputPort;
import hello.rent.application.usecase.CompensationUsecase;
import hello.rent.domain.model.RentalCard;
import hello.rent.domain.model.event.PointUseCommand;
import hello.rent.domain.model.vo.IDName;
import hello.rent.domain.model.vo.Item;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompensationInputPort implements CompensationUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalCard cancleRentItem(IDName idName, Item item) {

        return rentalCardOutputPort.loadRentalCard(idName.getId())
                .map(rentalCard -> {
                    try {
                        rentalCard.cancelRentItem(item);
                        eventOutputPort.occurPointUseCommand(new PointUseCommand(idName,10L));
                        return rentalCard;
                    } catch (Exception e){
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new NoSuchElementException("RentalCard not found for ID" + idName.getId()));
    }

    @Override
    public RentalCard cancleReturnItem(IDName idName, Item item, long point) {
        return rentalCardOutputPort.loadRentalCard(idName.getId())
                .map(rentalCard -> {
                    try {
                        rentalCard.cancelReturnItem(item, point);
                        eventOutputPort.occurPointUseCommand(new PointUseCommand(idName, point));
                        return rentalCard;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new NoSuchElementException("Rental card not found for ID: " + idName.getId()));
    }
    @Override
    public long cancleMakeAvailableRental(IDName idName, long point) {
        return rentalCardOutputPort.loadRentalCard(idName.getId())
                .map(rentalCard -> {
                    try {
                        return rentalCard.cancelMakeAvailableRental(point);
                        // 별도로 포인트 사용취소를 보상하기위한 이벤트는 발행하지 않음.
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new NoSuchElementException("Rental card not found for ID: " + idName.getId()));
    }
}
