package org.cytoscape.application.swing.search;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskObserver;


public abstract class AbstractNetworkSearchTaskFactory extends AbstractTaskFactory implements NetworkSearchTaskFactory {
	
	private static final int ICON_SIZE = 32;

	private String query;
	
	private final String id;
	private final String name;
	private final String description;
	private final Icon icon;
	private final URL website;
	
	protected AbstractNetworkSearchTaskFactory(String id, String name, Icon icon) {
		this(id, name, null, icon, null);
	}
	
	protected AbstractNetworkSearchTaskFactory(String id, String name, String description, Icon icon) {
		this(id, name, description, icon, null);
	}
	
	protected AbstractNetworkSearchTaskFactory(String id, String name, String description, Icon icon, URL website) {
		if (id == null || id.trim().isEmpty())
			throw new IllegalArgumentException("'id' must not be null or blank.");
		if (name == null || name.trim().isEmpty())
			throw new IllegalArgumentException("'name' must not be null or blank.");
		
		this.id = id;
		this.name = name;
		this.description = description;
		this.icon = icon != null ? icon : new ImageIcon(new RandomImage(ICON_SIZE, ICON_SIZE));
		this.website = website;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Icon getIcon() {
		return icon;
	}

	@Override
	public URL getWebsite() {
		return website;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	@Override
	public TaskObserver getTaskObserver() {
		return null;
	}
	
	@Override
	public JComponent getQueryComponent() {
		return null;
	}
	
	@Override
	public JComponent getOptionsComponent() {
		return null;
	}
	
	@Override
	public boolean isReady() {
		return query != null && !query.trim().isEmpty();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 11;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractNetworkSearchTaskFactory other = (AbstractNetworkSearchTaskFactory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
	
	private class RandomImage extends BufferedImage {

		private final int grain = 5;
		private final int colorRange = 5;
		
		public RandomImage(int width, int height) {
			super(width, height, BufferedImage.TYPE_INT_ARGB);
			draw();
		}
		
		private void draw() {
			int w = getWidth();
			int h = getHeight();
			
			Graphics2D g2 = (Graphics2D) getGraphics();
			g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON));

			int max = 200, min = 100;
			
			Color color = randomColor();
			int red = color.getRed();
			int green = color.getGreen();
			int blue = color.getBlue();
			
			g2.setColor(color);
			
			double blockout = Math.random();
			int x = 0, y = 0;
			
			for (int i = 0; i < grain; i++) {
				for (int j = 0; j < grain; j++) {
					if (blockout < 0.4) {
						g2.fillRect(x, y, w / grain, h / grain);
						g2.fillRect(w - x - w / grain, y, w / grain, h / grain);
						x += w / grain;
					} else {
						red -= colorRange;
						red = Math.min(max, Math.max(red, min));
						
						green += colorRange;
						green = Math.min(max, Math.max(green, min));
						
						blue += colorRange;
						blue = Math.min(max, Math.max(blue, min));
						
						g2.setColor(new Color(red, green, blue));
						x += w / grain;
					}
					
					blockout = Math.random();
				}
				
				y += h / grain;
				x = 0;
			}
		}
		
		private Color randomColor() {
			// Get rainbow, pastel colors
			Random random = new Random();
			final float hue = random.nextFloat();
			final float saturation = 0.9f;// 1.0 for brilliant, 0.0 for dull
			final float luminance = 1.0f; // 1.0 for brighter, 0.0 for black
			
			return Color.getHSBColor(hue, saturation, luminance);
		}
	}
}
