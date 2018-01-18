package hama.view;

import com.renaud.picross.model.PixelTable;

import hama.Hama;
import hama.HamaGenerator;
import hama.Nuancier;
import hama.Perle;
import hama.view.PaletteView.PerleToggleAction;

public class HamaGame implements StoreObserver {

	private PixelTable table;
	private Nuancier nuancier;

	private Hama hama;

	public HamaGame(PixelTable table, Nuancier nuancier) {
		this.table = table;
		this.nuancier = nuancier;
		this.hama = HamaGenerator.compute(table, nuancier.getPerles());

	}

	public Hama getHama() {
		return this.hama;
	}

	@Override
	public void notify(Action action) {
		if (action instanceof PerleToggleAction) {
			Perle perle = ((PerleToggleAction) action).getPerle();

			if (nuancier.estUtilisee(perle)) {
				nuancier.supprimer(perle);
			}
			else {
				nuancier.ajouter(perle);
			}
			this.hama = HamaGenerator.compute(table, nuancier.getPerles());
			Store.dispatch(new NuancierRenew());
			Store.dispatch(new HamaRenew(this.hama));
		}

	}

	public static class HamaRenew extends Action {

		private Hama hama;

		public HamaRenew(Hama hama) {
			super();
			this.hama = hama;
		}

		public Hama getHama() {
			return hama;
		}

	}

	public static class NuancierRenew extends Action {

	}

}
