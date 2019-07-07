package mypackage;

import javax.microedition.global.Formatter;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;
import net.rim.device.api.util.StringProvider;

public class CutOffCalcScreen extends MainScreen implements FieldChangeListener {

	/**
	 * 
	 */
	
	private Bitmap backgroundBitmap;
	VerticalFieldManager verticalManager;
	RichTextField rtfHeading;
	RichTextField rtfResult;
	RichTextField rtfScore;
	Font fontHeading = null;
	private EditField mathsEdit;
	private EditField physicsEdit;
	private EditField chemEdit;
	private LabelField mathsLabel;
	private LabelField physicsLabel;
	private LabelField chemLabel;
	ButtonField calculateButton;
	Border roundedBorder;
	
	
	public CutOffCalcScreen() {
		

		

		super(MainScreen.NO_HORIZONTAL_SCROLL | MainScreen.USE_ALL_WIDTH | MainScreen.USE_ALL_HEIGHT);
        // setTitle( "Points 2 Lines" );
        roundedBorder = BorderFactory.createRoundedBorder(new XYEdges(10,10,10,10), Color.WHITE, Border.STYLE_FILLED);
        
        backgroundBitmap = Bitmap.getBitmapResource("background.jpg");
        verticalManager  = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH | VerticalFieldManager.USE_ALL_HEIGHT){
        	 public void paint(Graphics graphics)
             {
                 //Draw the background image and then call paint.
                 graphics.drawBitmap(0, 0, Display.getWidth(),Display.getHeight(), backgroundBitmap, 0, 0);
                 super.paint(graphics);
             }
        };
        
        rtfHeading = new RichTextField("Cutoff Calculator", RichTextField.TEXT_ALIGN_HCENTER){
        	protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
        };
        rtfHeading.setMargin(20, 50, 20, 50);
        fontHeading = getFontToDisplay("Times New Roman", 55);
        rtfHeading.setFont(fontHeading);
        verticalManager.add(rtfHeading);
        
        mathsEdit = new EditField("","",10, TextField.NO_LEARNING | TextField.NO_NEWLINE | TextField.NON_SPELLCHECKABLE ){
            protected void paint(Graphics graphics) {
                graphics.clear();
                graphics.setBackgroundColor( Color.WHITE );
         
                super.paint(graphics);
              }
            };
            mathsEdit.setBorder(roundedBorder);
            mathsEdit.setMargin(20,20,30,20);
	    mathsLabel = new LabelField( "Math Score : " ){
            public void paint(Graphics g ){
                g.setColor(Color.WHITE);
                super.paint(g);
              }
            };
            mathsLabel.setFont( Font.getDefault().derive(Font.EMBOSSED_EFFECT,25,Ui.UNITS_px) );   
		verticalManager.add(mathsLabel);
		verticalManager.add(mathsEdit);
		
		physicsEdit = new EditField("","",10, TextField.NO_LEARNING | TextField.NO_NEWLINE | TextField.NON_SPELLCHECKABLE ){
            protected void paint(Graphics graphics) {
                graphics.clear();
                graphics.setBackgroundColor( Color.WHITE );
         
                super.paint(graphics);
              }
            };
            physicsEdit.setBorder(roundedBorder);
            physicsEdit.setMargin(20,20,30,20);
	    physicsLabel = new LabelField( "Physics Score : " ){
            public void paint(Graphics g ){
                g.setColor(Color.WHITE);
                super.paint(g);
              }
            };
            physicsLabel.setFont( Font.getDefault().derive(Font.EMBOSSED_EFFECT,25,Ui.UNITS_px) );   
		verticalManager.add(physicsLabel);
		verticalManager.add(physicsEdit);
		
		chemEdit = new EditField("","",10, TextField.NO_LEARNING | TextField.NO_NEWLINE | TextField.NON_SPELLCHECKABLE ){
            protected void paint(Graphics graphics) {
                graphics.clear();
                graphics.setBackgroundColor( Color.WHITE );
         
                super.paint(graphics);
              }
            };
            chemEdit.setBorder(roundedBorder);
            chemEdit.setMargin(20,20,30,20);
	    chemLabel = new LabelField( "Chemistry Score : " ){
            public void paint(Graphics g ){
                g.setColor(Color.WHITE);
                super.paint(g);
              }
            };
            chemLabel.setFont( Font.getDefault().derive(Font.EMBOSSED_EFFECT,25,Ui.UNITS_px) );   
		verticalManager.add(chemLabel);
		verticalManager.add(chemEdit);
		
		 rtfResult = new RichTextField("", RichTextField.TEXT_ALIGN_LEFT){
	        	protected void paint(Graphics g){ 
	                g.setColor(0xffffffff);
	                super.paint(g);
	            }
	        };
	        rtfResult.setMargin(60, 60, 50, 60);
	        fontHeading = getFontToDisplay("Georgia", 30);
	        rtfResult.setFont(fontHeading);
	        verticalManager.add(rtfResult);
	        
	        rtfScore = new RichTextField("", RichTextField.TEXT_ALIGN_LEFT){
	        	protected void paint(Graphics g){ 
	                g.setColor(Color.RED);
	                super.paint(g);
	            }
	        };
	        rtfScore.setMargin(10, 10, 5, 10);
	        fontHeading = getFontToDisplay("Georgia", 55);
	        rtfScore.setFont(fontHeading);
	       
	        
	        verticalManager.add(rtfScore);
		
		
	        fontHeading = getFontToDisplay("Comic Sans MS", 30);
	        calculateButton = new ButtonField( "Calculate", ButtonField.CONSUME_CLICK | FIELD_HCENTER );
	        calculateButton.setChangeListener(this);
	        calculateButton.setFont(fontHeading);
	        calculateButton.setMargin(55, 40, 0, 80);
	        
	      	        
		verticalManager.add(calculateButton);
		add(verticalManager);
	
		
		
	
		
	}
	
	
	public void fieldChanged(Field field, int context) {

		double math = 0;
		double phy = 0;
		double chem = 0;
		
		double cutOff = 0;
		
		Formatter format = new Formatter();

		if(field == calculateButton)
		{
			if(calculateButton.getLabel().equals("Calculate"))
			{
				calculateButton.setLabel("Reset");
				mathsEdit.setEditable(false);
				physicsEdit.setEditable(false);
				chemEdit.setEditable(false);

				try {
					math = Long.parseLong(mathsEdit.getText());
					phy = Long.parseLong(physicsEdit.getText());
					chem = Long.parseLong(chemEdit.getText());

					if(math < 0 && math > 200)
					{
						Dialog.inform("Math Score should be between 0 and 200");
					}
					else if(phy < 0 && phy > 200)
					{
						Dialog.inform("Physics Score should be between 0 and 200");
					}
					else if(chem < 0 && chem > 200)
					{
						Dialog.inform("Chemistry Score should be between 0 and 200");
					}
					else
					{
						cutOff = (math / 2) + ((phy + chem) / 4);
					}
					
					rtfResult.setText("Cutoff Score :");
					rtfScore.setText( "" +  format.formatNumber(cutOff,2) + " / 200");
				} catch (NumberFormatException e) {
					
					Dialog.inform("Please Enter Number!!!!");
					
				}
				catch (ArithmeticException e) {
					
						Dialog.inform("Error!!!! Please try after some time");
						UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());

				}
				catch (Exception e) {

					Dialog.inform("Error!!!! Please try after some time");
					UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());

				}
				
			}
			else
			{
				calculateButton.setLabel("Calculate");
				mathsEdit.setText("");
				physicsEdit.setText("");
				chemEdit.setText("");
				rtfResult.setText("");
				rtfScore.setText("");
				mathsEdit.setEditable(true);
				physicsEdit.setEditable(true);
				chemEdit.setEditable(true);
			}
		}
		else
		{
			Dialog.inform("Error!!! Please try after some time.");
		}
		
	}
	
	private Font getFontToDisplay(String stFontName, int fontSize)
    {
    	try
        {
            FontFamily ff1 = FontFamily.forName(stFontName);
            fontHeading = ff1.getFont(Font.ITALIC | Font.EXTRA_BOLD , fontSize);
            return fontHeading;
        }
        catch (Exception e) {
			e.printStackTrace();
			Dialog.inform("Error Occurred. Please try after some time");
			return null;
		}
    }
    
    protected boolean onSavePrompt() {
        return true;
    }

}
