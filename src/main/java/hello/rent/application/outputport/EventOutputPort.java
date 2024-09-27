package hello.rent.application.outputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import hello.rent.domain.model.event.ItemRented;
import hello.rent.domain.model.event.ItemReturned;
import hello.rent.domain.model.event.OverdueCleared;
import hello.rent.domain.model.event.PointUseCommand;

public interface EventOutputPort {
    public void occurRentalEvent(ItemRented itemRented) throws JsonProcessingException;
    public void occurReturnEvent(ItemReturned itemReturned) throws JsonProcessingException;
    public void occurOverdueClearedEvent(OverdueCleared overdueCleared) throws JsonProcessingException;

    void occurPointUseCommand(PointUseCommand pointUseCommand);
}
