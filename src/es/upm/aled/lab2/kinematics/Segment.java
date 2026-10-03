package es.upm.aled.lab2.kinematics;

import java.util.ArrayList;
import java.util.List;

public class Segment {
	
	private double length; //atributos
	private double angle;
	private List<Segment> children;
	
	public Segment(double length, double angle) { //constructor
		this.length = length;
		this.angle = angle;
		this.children = new ArrayList<>();
	}
	
	public double getLength() { //devuelve la longitud del segmento
		return this.length;
	}
	
	public double getAngle() { //devuelve el ángulo del segmento
		return this.angle;
	}
	
	public void setAngle(double angle) { //modifica el valor del ángulo del segmento
		this.angle = angle;
	}
	
	public List<Segment> getChildren() { //devuelve la lista de segmentos hijos
		return this.children;
	}
	
	public void addChild(Segment child) { //añade un segmento hijo a la lista si no está añadido
		if(!this.children.contains(child)) {
			this.children.add(child);
		}
	}
}
