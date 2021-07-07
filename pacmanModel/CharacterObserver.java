package pacmanModel;

import java.util.List;


public interface CharacterObserver {
	public void notify(List<CharacterEvent> events) ; 
}

