package mypackage;

import net.rim.device.api.ui.UiApplication;

/**
 * This class extends the UiApplication class, providing a graphical user interface.
 */
public class CutoffCalc extends UiApplication {
    /**
     * Entry point for application
     * 
     * @param args
     *            Command line arguments (not used)
     */
    public static void main( String[] args ) {
        // Create a new instance of the application and make the currently
        // running thread the application's event dispatch thread.
        CutoffCalc theApp = new CutoffCalc();
        theApp.enterEventDispatcher();
    }

    /**
     * Creates a new CutoffCalc object
     */
    public CutoffCalc() {
        // Push a screen onto the UI stack for rendering.
        pushScreen( new CutOffCalcScreen() );
    }
}
