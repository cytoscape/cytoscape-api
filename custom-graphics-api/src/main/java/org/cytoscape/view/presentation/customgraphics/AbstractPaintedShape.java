package org.cytoscape.view.presentation.customgraphics;

import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

public class AbstractPaintedShape implements PaintedShape {
	protected Shape shape;
	protected Paint fill;
	protected Stroke stroke;
	protected Paint strokePaint;

	public AbstractPaintedShape(Shape shape, Paint fill,
	                            Stroke stroke, Paint strokePaint) {
		this.shape = shape;
		this.fill = fill;
		this.stroke = stroke;
		this.strokePaint = strokePaint;
	}

	public Shape getShape(Rectangle2D bounds) {
		return shape;
	}

	public Paint getFill(Rectangle2D bounds) {
		return fill;
	}

	public Stroke getStroke() {
		return stroke;
	}

	public Paint getStrokePaint() {
		return strokePaint;
	}
}
