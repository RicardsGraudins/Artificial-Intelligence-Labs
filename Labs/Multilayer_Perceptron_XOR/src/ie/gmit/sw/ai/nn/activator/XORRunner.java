package ie.gmit.sw.ai.nn.activator;

import ie.gmit.sw.ai.nn.BackpropagationTrainer;
import ie.gmit.sw.ai.nn.NeuralNetwork;

public class XORRunner {
	
	public static void main(String[] args) throws Exception {
		double[][] data = {{0, 0}, {1, 0}, {0, 1}, {1, 1}};
		double[][] expected = {{0}, {1}, {1}, {0}};
		
		NeuralNetwork nn = new NeuralNetwork(Activator.ActivationFunction.Sigmoid, 2, 2, 1);
		
		BackpropagationTrainer trainer = new BackpropagationTrainer(nn);
		trainer.train(data, expected, 0.01, 1000000);
		
		double[] test1 = {0.0, 0.0};
		double[] test2 = {1.0, 0.0};
		double[] test3 = {0.0, 1.0};
		double[] test4 = {1.0, 1.0};
		
		System.out.println("00=>" + getRoundedValue(nn.process(test1)));
	}
	
	public static long getRoundedValue(double[] vector){
		 return Math.round(vector[0]);
		 
		}

}