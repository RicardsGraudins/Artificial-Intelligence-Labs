package ie.gmit.sw;

import net.sourceforge.jFuzzyLogic.*;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Runner {

	public static void main(String[] args) throws Exception {
		FIS fis = FIS.load("conf/staffing", true); //Load and parse the FCL
		//fis.chart(); //Display the linguistic variables and terms
		
		//fis.chart(); Is outdated
		FunctionBlock fb = fis.getFunctionBlock("staffing");
		JFuzzyChart.get().chart(fb);
		
		
		fis.setVariable("funding", 60); //Apply a value to a variable
		fis.setVariable("staffing", 14);
		fis.evaluate(); //Execute the fuzzy inference engine
		System.out.println(fis.getVariable("risk").getValue()); //Output end result
		
		//Get the center of gravity chart
		Variable risk = fb.getVariable("risk");
		JFuzzyChart.get().chart(risk,risk.getDefuzzifier(), true);
	}//main
}//Runner