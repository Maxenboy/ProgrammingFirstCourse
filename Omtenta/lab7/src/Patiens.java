import se.lth.cs.ptdc.cardGames.Card;
import se.lth.cs.ptdc.cardGames.CardDeck;

public class Patiens {
	public static void main(String[] args) {
		double success = 0;
		for (int i = 0; i < 10000; i++) {
			boolean fail = false;
			CardDeck deck = new CardDeck();
			deck.shuffle();
			int n = 0;
			while (deck.moreCards() && fail == false) {
				Card c = deck.getCard();
				if (c.getRank() == (n % 3) + 1) {
					fail = true;
				}
				n++;
			}
			if (fail == false) {
				success++;
			}
		}
		System.out.println("Ration på lyckade patience-spel:"
				+ (success / 10000));
	}
}
