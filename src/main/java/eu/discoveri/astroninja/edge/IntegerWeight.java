/*
 * Copyright 2014 Chris Powell, CPgraph Ltd.
 * http://CPgraph.com
 */
package eu.discoveri.astroninja.edge;

import eu.discoveri.astroninja.config.Const;
import javax.persistence.Entity;


/**
 * Weight (Integer) for a given Edge class.  Implements {@link Weight Weight} to
 * provide integer weighting for edges.
 *
 * @author Chris Powell, CPgraph Ltd.
 */
@Entity
public class IntegerWeight extends AbstractWeight
{
    //<editor-fold defaultstate="collapsed" desc="Attributes">
    private int weight;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /*
     * No argument constructor.
     */
    protected IntegerWeight(){}
    /**
     * Constructor with given integer weight.
     * @param weight integer value of weight
     */
    public IntegerWeight(int weight)
    {
        this.weight = weight;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutators">
    /**
     * Get the Integer weight
     * @return Weight
     */
    @Override
    public Integer getWeight()
    {
        return weight;
    }

    /**
     * Set the weight to the Integer value of the input object
     * @param weight
     */
    @Override
    public void setWeight(Object weight)
    {
        if( weight == null )
            {  this.weight = 0;  }
        else
            {  this.weight = (Integer)weight;  }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Min/Max Weight">
    /** {@inheritDoc}
     */
    @Override
    public Integer getMaxWeight()
    {
        return (Integer)Integer.MAX_VALUE;
    }
    /** {@inheritDoc}
     */
    @Override
    public Integer getMinWeight()
    {
        return (Integer)Integer.MIN_VALUE;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="add()">
    /** {@inheritDoc}
     */
    @Override
    public Object add( Object weight )
    {
        return this.weight + (Integer)weight;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Compare to">
    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object.
     *
     * @see java.lang.Comparable java.lang.Comparable
     *
     * @param inWeight the Weight object to be compared
     * @return A negative integer, zero, or a positive integer as this object is
     * less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo( Object inWeight )
    {
      int inw;

        if( inWeight == null )
            {  inw = 0;  }
        else
            {  inw = (Integer)inWeight;  }

        if( weight < inw )
            return Const.BEFORE;
        else
            if( weight > inw )
                return Const.AFTER;
            else
                return Const.EQUAL;
    }
    //</editor-fold>
}
