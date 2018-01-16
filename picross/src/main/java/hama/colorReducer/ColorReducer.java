package hama.colorReducer;

import java.util.List;

import com.renaud.picross.model.Couleur;

public interface ColorReducer {

	public Couleur reduce(List<Couleur> couleurs);
}
