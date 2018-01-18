package hama.view;

import java.awt.event.MouseEvent;

import com.renaud.picross.view.IDrawable;

import hama.Nuancier;
import hama.Perle;
import hama.drawer.PaletteDrawer;

public class PaletteView {

	public PaletteView(PaletteDrawer drawable, Nuancier nuancier, int x, int y, int size) {
		int i = 0;
		for (Perle perle : nuancier.getPerles()) {
			int xi = x + (size + 1) * i++;
			PerleZone z = MouseZoneProvider.getInstance().createZone(PerleZone.class, xi, y, size, size);
			z.setDrawable(drawable);
			z.setPerle(perle);
		}
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
			Store.dispatch(new PerleOver(perle));
		}

		@Override
		public void mouseExited() {
			if (perle.getCouleur().equals(this.drawable.getCouleurActive())) {
				this.drawable.setCouleurActive(null);
				Store.dispatch(new PerleOver(null));
			}
		}

		@Override
		public void mouseMove(int x, int y) {

		}

		@Override
		public void mousePressed(int button, int x, int y) {
			if (button == MouseEvent.BUTTON1) {
				Store.dispatch(new PerleToggleAction(perle));
			}
		}

		@Override
		public void mouseReleased(int button, int x, int y) {
			// TODO Auto-generated method stub

		}

	}

	public static class PerleToggleAction extends Action {

		private Perle perle;

		public Perle getPerle() {
			return perle;
		}

		public PerleToggleAction(Perle perle) {
			super();
			this.perle = perle;
		}

	}

	public static class PerleOver extends Action {

		Perle perle;

		public PerleOver(Perle perle) {
			this.perle = perle;
		}

		public Perle getPerle() {
			return perle;
		}
	}
}
