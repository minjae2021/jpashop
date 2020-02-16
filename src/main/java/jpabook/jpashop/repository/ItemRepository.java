package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        /*item 객체는 생성되고 id가
          주어지므로 밑에 있는 로직은
          새로 생성되는 객체인지 아닌지 판단
          * */
        if(item.getId()==null){
            em.persist(item);
        }else{
            em.merge(item);
            //강제 업데이트
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }
}
