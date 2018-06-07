package ie.gmit.sw.ai.nn.activator;

import ie.gmit.sw.ai.nn.BackpropagationTrainer;
import ie.gmit.sw.ai.nn.NeuralNetwork;
import ie.gmit.sw.ai.nn.Utils;

public class GameRunner {
	
	public static void main(String[] args) throws Exception {
		/*
		 *  Inputs
		 *  -------------
		 *  1) Health (2 = Healthy, 1 = Minor Injuries, 0 = Serious Injuries)
		 *  2) Has a Sword (1 = yes, 0 = no)
		 *  3) Has a Gun (1 = yes, 0 = no)
		 *  4) Number of Enemies (This value may need to be normalized)
		 *  
		 *  Outputs
		 *  -------------
		 *  1) Panic
		 *  2) Attack
		 *  3) Hide
		 *  4) Run
		 */

		 double[][] data = { //Health, Sword, Gun, Enemies
				{ 2, 0, 0, 0 }, { 2, 0, 0, 1 }, { 2, 0, 1, 1 }, { 2, 0, 1, 2 }, { 2, 1, 0, 2 },
				{ 2, 1, 0, 1 }, { 1, 0, 0, 0 }, { 1, 0, 0, 1 }, { 1, 0, 1, 1 }, { 1, 0, 1, 2 }, 
				{ 1, 1, 0, 2 }, { 1, 1, 0, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 1 }, 
				{ 0, 0, 1, 2 }, { 0, 1, 0, 2 }, { 0, 1, 0, 1 } };

		 double[][] expected = { //Panic, Attack, Hide, Run
				{ 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0 }, 
				{ 0.0, 0.0, 0.0, 1.0 }, { 1.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 }, 
				{ 1.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0, 1.0 }, 
				{ 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 1.0, 0.0, 0.0 }, 
				{ 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };
		
		NeuralNetwork nn = new NeuralNetwork(Activator.ActivationFunction.Sigmoid, 4, 3, 4);
		
		BackpropagationTrainer trainer = new BackpropagationTrainer(nn);
		trainer.train(data, expected, 0.6, 10000);
		
		int testIndex = 11;
		double[] result = nn.process(data[testIndex]);
		for (int i = 0; i < expected[testIndex].length; i++){
		 System.out.print(expected[testIndex][i] + ",");
		}
		System.out.println("==>" + (Utils.getMaxIndex(result) + 1));
	}

}
