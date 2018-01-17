package hama.view;

import com.renaud.picross.view.IDrawable;

import hama.Nuancier;
import hama.Perle;
import hama.drawer.PaletteDrawer;

public class PaletteView {

	private PaletteDrawer drawable;
	private Nuancier nuancier;

	public PaletteView(PaletteDrawer drawable, Nuancier nuancier, int x, int y, int size) {
		this.drawable = drawable;
		this.nuancier = nuancier;

		int i = 0;
		for (Perle perle : nuancier.getPerles()) {
			int xi = x + (size + 1) * i++;
			PerleZone z = MouseZoneProvider.getInstance().createZone(PerleZone.class, xi, y, size, size);
			z.setDrawable(drawable);
			z.setPerle(perle);
		}

		// MouseZone zone = MouseZoneProvider.getInstance().createZone(MouseZone.class, 10, 10, 200, 200);
	}

	public static class PerleZone extends MouseZone {

		private PaletteDrawer drawable;
		private Perle perle;

		public IDrawable getDrawable() {
			return drawable;
		}

		public void setDrawable(PaletteDrawer drawable) {
			this.drawable = drawable;
		}

		public Perle getPerle() {
			return perle;
		}

		public void setPerle(Perle perle) {
			this.perle = perle;
		}

		@Override
		public void mouseEntered(int x, int y) {
			this.drawable.setCouleurActive(perle.getCouleur());

		}

		@Override
		public void mouseExited() {
			if (perle.getCouleur().equals(this.drawable.getCouleurActive())) {
				this.drawable.setCouleurActive(null);
			}
		}

		@Override
		public void mouseMove(int x, int y) {
			// TODO Auto-generated method stub

		}

	}
}
