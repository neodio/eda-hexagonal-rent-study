package hello.rent.framework.web.dto;

import hello.rent.domain.model.vo.ReturnItem;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReturnItemOutputDTO {
    private Integer itemNo;
    private String itemtitle;
    private LocalDate returnDate;

    public static ReturnItemOutputDTO mapToDTO(ReturnItem returnItem)
    {
        ReturnItemOutputDTO rentItemOutputDTO = new ReturnItemOutputDTO();
        rentItemOutputDTO.setItemNo(returnItem.getRentalItem().getItem().getNo());
        rentItemOutputDTO.setItemtitle(returnItem.getRentalItem().getItem().getTitle());
        rentItemOutputDTO.setReturnDate(returnItem.getReturnDate());
        return rentItemOutputDTO;
    }
}
