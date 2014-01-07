package org.rapla.plugin.studiinf.client.search;

import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings("serial")
class NoDuplicatesList<E> extends LinkedList<E> {
    @Override
    public boolean add(E e) {
        if (this.contains(e)) {
            return false;
        }
        else {
            return super.add(e);
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
    	if(collection !=null){
        Collection<E> copy = new LinkedList<E>(collection);
        copy.removeAll(this);
        return super.addAll(copy);
    	}else{
    		return false;
    	}
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
    	if(collection !=null){
        Collection<E> copy = new LinkedList<E>(collection);
        copy.removeAll(this);
        return super.addAll(index, copy);
    	}else{
    		return false;
    	}
    }

    @Override
    public void add(int index, E element) {
        if (this.contains(element)) {
            return;
        }
        else {
            super.add(index, element);
        }
    }
}   