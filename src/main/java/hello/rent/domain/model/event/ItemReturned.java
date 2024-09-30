package hello.rent.domain.model.event;

import hello.rent.domain.model.vo.IDName;
import hello.rent.domain.model.vo.Item;
import lombok.Getter;

@Getter
public class ItemReturned extends ItemRented{
    public ItemReturned(IDName idName, Item item, long point) {
        super(idName, item, point);
    }
}
