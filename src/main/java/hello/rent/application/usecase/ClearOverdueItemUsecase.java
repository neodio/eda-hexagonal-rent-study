package hello.rent.application.usecase;


import hello.rent.framework.web.dto.ClearOverdueInfoDTO;
import hello.rent.framework.web.dto.RentalResultOutputDTO;

public interface ClearOverdueItemUsecase {
    RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO) throws Exception;
}
