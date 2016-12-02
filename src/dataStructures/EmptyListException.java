/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package dataStructures;

public class EmptyListException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public EmptyListException( )
    {
        super();
    }

    public EmptyListException( String message )
    {
        super(message);
    }

}

