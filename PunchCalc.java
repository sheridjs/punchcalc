// Jay Sheridan
// 3/21/03

// Coded up in about 2 hours. :-)

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.lang.Math;
import java.lang.Double;
import java.lang.String;
import CloseableFrame;

public class PunchCalc extends Applet implements ActionListener
{
    private Button btn_Calc;
    private TextField txf_DamageNo, txf_Result;
    private Choice chc_PunchType;
    private Label lbl_PunchType, lbl_DamageNo, lbl_Result;
    private Panel pnl_Main, pnl_DamageNo, pnl_Result;

    public static void main(String[] args)
    // Initializing code for standalone
    {
	Frame f = new CloseableFrame();

	f.setTitle("SSBM Punch Calculator");
	f.setLayout(new FlowLayout());
	f.add(new PunchCalc());
	f.pack();
	f.show();
    }

    public void init()
    // Initializing code for applets
    {
	buildObjects();
	setLayout();
	//btn_Calc.addActionListener(this);
    }

    public PunchCalc()
    // Object constructor
    {
	init();
    }

    private void buildObjects() 
    {
	// Buttons
	btn_Calc = new Button("Calculate");
	btn_Calc.addActionListener(this);

	// Labels    
	lbl_PunchType = new Label("Punch Type"); // Might not need
	lbl_DamageNo = new Label("Starting Damage %");
	lbl_Result = new Label("Result");

	// Text Fields
	txf_DamageNo = new TextField("0",3);
	txf_Result = new TextField("0",8);
	txf_Result.setEditable(false);

	// Choice box
	chc_PunchType = new Choice();
	chc_PunchType.addItem("27% Falcon Punch");
	chc_PunchType.addItem("30% Warlock Punch");
	chc_PunchType.addItem("Reverse Warlock Punch");

	// Panels
	pnl_Main = new Panel(new GridLayout(4,1));
	pnl_DamageNo = new Panel(new FlowLayout());
	pnl_Result = new Panel(new FlowLayout());
    }

    private void setLayout() 
    {
	pnl_DamageNo.add(lbl_DamageNo);
	pnl_DamageNo.add(txf_DamageNo);

	pnl_Result.add(lbl_Result);
	pnl_Result.add(txf_Result);

	pnl_Main.add(chc_PunchType);
	pnl_Main.add(pnl_DamageNo);
	pnl_Main.add(btn_Calc);
	pnl_Main.add(pnl_Result);

	setLayout(new FlowLayout());
	add(pnl_Main);
    }

    public void actionPerformed(ActionEvent evt)
    {
	double damageNo, resultNo;

	resultNo = -1.0;
	//txf_Result.setText(evt.getActionCommand());
	if( evt.getActionCommand() == "Calculate") {
	    try {
		damageNo = Double.parseDouble(txf_DamageNo.getText());
	    }
	    catch(NumberFormatException e) {
		txf_Result.setText("Bad Number");
		return;
	    }

	    switch(chc_PunchType.getSelectedIndex()) {
	    case 0: // 27% Falcon Punch
		//txf_Result.setText(chc_PunchType.getItem(0));
		// =0.000000000021951523*x^6 - 0.000000012626201237*x^5 + 0.000002304480124948*x^4 - 0.000000397044232159*x^3 + 0.0350101543663186*x^2 + 2.35894427658058*x + 43.3
		resultNo = 0.000000000021951523*Math.pow(damageNo,6) - 0.000000012626201237*Math.pow(damageNo,5) + 0.000002304480124948*Math.pow(damageNo,4) - 0.000000397044232159*Math.pow(damageNo,3) + 0.0350101543663186*Math.pow(damageNo,2) + 2.35894427658058*damageNo + 43.3;
		break;
	    case 1: // 30% Warlock Punch
		//txf_Result.setText(chc_PunchType.getItem(1));
		// =0.0000000000640339*x^6 - 0.0000000280316505*x^5 + 0.0000036014349343*x^4 + 0.0000734186955356*x^3 + 0.0370446199503931*x^2 + 3.0626835883595*x + 57
		resultNo = 0.0000000000640339*Math.pow(damageNo,6) - 0.0000000280316505*Math.pow(damageNo,5) + 0.0000036014349343*Math.pow(damageNo,4) + 0.0000734186955356*Math.pow(damageNo,3) + 0.0370446199503931*Math.pow(damageNo,2) + 3.0626835883595*damageNo + 57.0;
		break;
	    case 2: // Reverse Warlock Punch
		//txf_Result.setText(chc_PunchType.getItem(2));
		// =0.0000000000215085*x^6 - 0.0000000202643728*x^5 + 0.0000074004847015*x^4 - 0.0009583113146618*x^3 + 0.1597290042846*x^2 + 5.13411571271717*x + 189.9
		resultNo = 0.0000000000215085*Math.pow(damageNo,6) - 0.0000000202643728*Math.pow(damageNo,5) + 0.0000074004847015*Math.pow(damageNo,4) - 0.0009583113146618*Math.pow(damageNo,3) + 0.1597290042846*Math.pow(damageNo,2) + 5.13411571271717*damageNo + 189.9;
		break;
	    default:
		break;		
	    }
	    if(resultNo < 0) {
		txf_Result.setText("Error: Division by 5");
	    }
	    else {
		// shove result in textfield
		txf_Result.setText(String.valueOf(resultNo));
	    }
	}
	else {;}
    }
    
}

