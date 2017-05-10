package tools;


import javafx.scene.control.MenuItem;

/**
 * Created by David on 23/04/2017.
 */
public class WrappedMenuItem <O> extends MenuItem {
    O wrappedObject;

    public WrappedMenuItem(String text, O obj)
    {
        super(text);
        setWrapped(obj);
    }
    public O getWrapped()
    {
        return wrappedObject;
    }

    public void setWrapped(O obj)
    {
        wrappedObject = obj;
    }
}
