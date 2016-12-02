/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package dataStructures;

public class EmptyQueueException extends RuntimeException
{                                     

    static final long serialVersionUID = 0L;


    public EmptyQueueException( )   
    {
        super();
    }

    public EmptyQueueException( String message )
    {
        super(message);
    }

}
