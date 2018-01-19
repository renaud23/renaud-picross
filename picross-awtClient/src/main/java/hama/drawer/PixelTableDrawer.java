package hama.drawer;

import java.awt.Color;

import com.renaud.picross.model.PixelTable;
import com.renaud.picross.view.DrawOperationAware;
import com.renaud.picross.view.IDrawOperation;
import com.renaud.picross.view.IDrawable;
import com.renaud.picross.view.JImageBuffer;

public class PixelTableDrawer implements IDrawable, DrawOperationAware {

	private IDrawOperation drawer;

	private PixelTable table;
	private IDrawOperation buffer;
	private int margeX = 5;
	private int margeY = 5;
	private int pixelSize = 5;
	private int marge = 1;
	private boolean filled = false;

	public PixelTableDrawer(PixelTable table) {
		this.table = table;
		buffer = new JImageBuffer(Color.white, table.getLargeur() * pixelSize, table.getHauteur() * pixelSize);
	}

	public PixelTableDrawer(PixelTable table, int margeX, int margeY, int pixelSize, int marge) {
		this.table = table;
		this.margeX = margeX;
		this.margeY = margeY;
		this.pixelSize = pixelSize;
		buffer = new JImageBuffer(Color.white, table.getLargeur() * pixelSize, table.getHauteur() * pixelSize);
		this.marge = marge;
	}

	public void setDrawOperation(IDrawOperation op) {
		this.drawer = op;
	}

	@Override
	public boolean isChange() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		if (!filled) {
			buffer.transparentClean();
			for (int i = 0; i < table.getHauteur(); i++) {

				for (int j = 0; j < table.getLargeur(); j++) {
					int xi = j * pixelSize;
					int yi = i * pixelSize;

					buffer.fillRect(new Color(table.getPixel(j, i).getR(), table.getPixel(j, i).getG(), table.getPixel(j, i).getB(), table.getPixel(j, i).getAlpha()), xi, yi, pixelSize - marge, pixelSize - marge, 1.0f);
				}
			}
			filled = true;
		}
		this.drawer.drawImage(buffer.getImage(), margeX, margeY, 0, 0, 0, 1.0, 1.0f);

	}

	public void FillImage() {
		buffer.transparentClean();
		for (int i = 0; i < table.getHauteur(); i++) {

			for (int j = 0; j < table.getLargeur(); j++) {
				int xi = j * pixelSize;
				int yi = i * pixelSize;

				buffer.fillRect(new Color(table.getPixel(j, i).getR(), table.getPixel(j, i).getG(), table.getPixel(j, i).getB(), table.getPixel(j, i).getAlpha()), xi, yi, pixelSize - marge, pixelSize - marge, 1.0f);
			}
		}
	}

	public IDrawOperation getBuffer() {
		return buffer;
	}

	public void setBuffer(IDrawOperation buffer) {
		this.buffer = buffer;
	}

}
