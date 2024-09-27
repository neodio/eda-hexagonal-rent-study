package hello.rent.domain.model.event;

import hello.rent.domain.model.vo.IDName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PointUseCommand implements Serializable {
    private IDName idName;
    private long point;
}