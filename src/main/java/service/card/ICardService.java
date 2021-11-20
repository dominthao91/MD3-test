package service.card;

import model.Card;

import java.util.List;

public interface ICardService {
    List<Card> showAll();
    Card findById(int id);
    boolean add(Card card);
    boolean edit(int id);
    boolean delete(int id);
}
