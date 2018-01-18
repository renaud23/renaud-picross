package hama.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class MouseZoneProvider implements MouseListener, MouseMotionListener {

	private static MouseZoneProvider instance;
	private List<MouseZone> zones = new ArrayList<>();
	private List<MouseZone> actives = new ArrayList<>();

	private MouseZoneProvider() {}

	public static MouseZoneProvider getInstance() {
		if (instance == null) {
			instance = new MouseZoneProvider();
		}
		return instance;
	}

	public <U extends MouseZone> U createZone(Class<U> clazz, int x, int y, int width, int height) {
		try {
			U zone = clazz.newInstance();
			zone.setX(x);
			zone.setY(y);
			zone.setWidth(width);
			zone.setHeight(height);
			this.zones.add(zone);

			return zone;
		}
		catch (InstantiationException | IllegalAccessException e) {
			throw new UnsupportedOperationException();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (MouseZone zone : actives) {
			zone.mousePressed(e.getButton(), e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (MouseZone zone : actives) {
			zone.mouseReleased(e.getButton(), e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (MouseZone zone : zones) {
			boolean isActive = actives.contains(zone);
			if (zone.isIn(e.getX(), e.getY())) {
				if (!isActive) {
					actives.add(zone);
					zone.mouseEntered(e.getX() - zone.getX(), e.getY() - zone.getY());
				}
			}
			else
				if (isActive) {
					actives.remove(zone);
					zone.mouseExited();
				}
		}
		actives.forEach(zone -> zone.mouseMove(e.getX() - zone.getX(), e.getY() - zone.getY()));
	}

}
