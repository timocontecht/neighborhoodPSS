package org.visico.neighborhoodpss.gwt.shared.patterns;

import java.util.HashSet;
import java.util.Iterator;



public abstract class Subject 
{
	public void addObserver(ObserverInterface o) {
		observers.add(o);
		
	}

	public void removeObserver(ObserverInterface o) {
		observers.remove(o);
		
	}
	
	protected void notifyObservers() {
        // loop through and notify each observer
        Iterator<ObserverInterface> i = observers.iterator();
        while( i.hasNext() ) {
              ObserverInterface o = ( ObserverInterface ) i.next();
              o.update( this );
        }
  }
	
	protected HashSet<ObserverInterface> observers = new HashSet<ObserverInterface>();
}
