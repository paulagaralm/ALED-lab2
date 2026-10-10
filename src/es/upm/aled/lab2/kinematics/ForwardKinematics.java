package es.upm.aled.lab2.kinematics;

import es.upm.aled.lab2.gui.Node;

/**
 * This class implements a forward kinematics algorithm using recursion. It
 * expects a tree of Segments (defined by its length and angle with respect to
 * the previous Segment in the tree) and returns a tree of Nodes (defined by
 * their absolute coordinates in a 2-dimensional space).
 * 
 * @author rgarciacarmona
 */
public class ForwardKinematics {

	/**
	 * Returns a tree of Nodes to be used by SkeletonPanel to draw the position of
	 * an exoskeleton. This method is the public facade to a recursive method that
	 * builds the result from a tree of Segments defined by their angle and length,
	 * and the relationship between them (which Segment is children of which).
	 * 
	 * @param root    The root of the tree of Segments.
	 * @param originX The X coordinate for the origin point of the tree.
	 * @param originY The Y coordinate for the origin point of the tree.
	 * @return The tree of Nodes that represent the exoskeleton position in absolute
	 *         coordinates.
	 */
	// Public method: returns the root of the position tree
	public static Node computePositions(Segment root, double originX, double originY) {
		return computePositions(root, originX, originY, 0);
	}

	// Private helper method that implements the recursive algorithm
	private static Node computePositions(Segment link, double baseX, double baseY, double accumulatedAngle) {
		//medida de tiempos
		long startTime = System.nanoTime(); //instante de tiempo en el que comienza el método
		
		//código general
		Node nodoDeTurno = new Node(baseX, baseY); //nodo actual al inicio del segmento
		double anguloTotal = accumulatedAngle + link.getAngle(); //suma de todos los ángulos anteriores y el actual
		double posicionX = nodoDeTurno.getX() + link.getLength()*Math.cos(anguloTotal); //posición del nodo nuevo en x
		double posicionY = nodoDeTurno.getY() + link.getLength()*Math.sin(anguloTotal); //posición del nodo nuevo en y
		Node nodoSiguiente = new Node(posicionX, posicionY); //nodo nuevo al final del segmento
		nodoDeTurno.addChild(nodoSiguiente); //se añade el nodo hijo a la lista del nodo padre
		//paso recursivo y caso base
		for(Segment segmento : link.getChildren()) { //para cada segmento hijo
			Node nodoHijoCalculado = computePositions(segmento, posicionX, posicionY, anguloTotal); //se llama a sí mismo
			nodoSiguiente.addChild(nodoHijoCalculado); //se añade el nodo hijo a la lista del nodo padre
		}
		
		//medida de tiempos
		long runningTime = System.nanoTime() - startTime; //calcula cuánto ha tardado en ejecutarse el método
		System.out.println("Tiempo de computePositions para un segmento con " + link.getChildren().size() + " hijos: " + runningTime + " nanosegundos"); //muestra por pantalla dicho tiempo
		
		return nodoDeTurno; //devuelve el nodo raíz
	}
}