//Heidy Rodriguez
//Programming Fundamentals
//Summer 2021
//Programming Assignment 2 (NearestNeighbor)

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NearestNeighbor {

	public static void main(String[] args) throws FileNotFoundException { 
		heading();
		
		System.out.println("Programming Fundamentals"); //start message welcome
		System.out.println("NAME: HEIDY RODRIGUEZ");
		System.out.println("PROGRAMMING ASSIGNMENT 3");

		//creating empty value for arrays
		double [][] testingVal= new double[75][4];
		double [][] trainingVal= new double [75][4];
		String trainingClassLabel [] = new String [75];
		String testingClassLabel [] = new String [75];
		String predictedClassLabel [] = new String [75];
		
			//user insert file names
			Scanner input = new Scanner(System.in);
			System.out.println("Please enter name of training file: ");
			input.nextLine();
			System.out.println("Please enter name of testing file: ");
			input.nextLine();
			
			//array for the training val
			File trainingFile = new File("/Users/HeidyRodriguez/Desktop/Cyber Security/Summer 2021 Courses/Programming Fundamentals/Week 6/Program 3/iris-training-data.csv");
			Scanner fileScanTraining = new Scanner(trainingFile);
				for(int i = 0; fileScanTraining.hasNextLine(); i++) {
					String str = fileScanTraining.nextLine();
					String[] numbers = str.split(",", 5);
					trainingClassLabel[i] = numbers[4];
						for (int j = 0; j < 4; j++) {
							trainingVal[i][j] = Double.parseDouble(numbers[j]);
						}
				}
			//array for testing val	
			File testingFile = new File("/Users/HeidyRodriguez/Desktop/Cyber Security/Summer 2021 Courses/Programming Fundamentals/Week 6/Program 3/iris-testing-data.csv");
			Scanner fileScanTesting = new Scanner(testingFile);
				for (int i = 0; fileScanTesting.hasNextLine(); i++) {
					String str = fileScanTesting.nextLine();
					String[] numbers = str.split(",", 5);
					testingClassLabel[i] = numbers[4];
						for (int j = 0; j < 4; j++) {
							testingVal[i][j] = Double.parseDouble(numbers[j]);
						}
				}
			
			//Make sure for accuracy
			accuracy(testingClassLabel, prediction(testingVal,trainingVal,trainingClassLabel,predictedClassLabel));
			
			//closing scanners here
			fileScanTraining.close();
			fileScanTesting.close();
			input.close();
	
	}
	//Distance formula insert here
	public static int leastdistance(double sly, double swy, double ply, double pwy, double[][] trainingVal) {
		
			int rowNum = 0;
			double slTotal=Math.pow((trainingVal[0][0]-sly),2);
		    double swTotal=Math.pow((trainingVal[0][1]-swy),2);
		    double plTotal=Math.pow((trainingVal[0][2]-ply),2);
		    double pwTotal=Math.pow((trainingVal[0][3]-pwy),2);
		    double distance=Math.sqrt(slTotal+swTotal+plTotal+pwTotal);
		     for(int i = 0; i < 75; i++) {
		           double slTest=Math.pow((trainingVal[i][0]-sly),2);
		           double swTest=Math.pow((trainingVal[i][1]-swy),2);
		           double plTest=Math.pow((trainingVal[i][2]-ply),2);
		           double pwTest=Math.pow((trainingVal[i][3]-pwy),2);
		           double testDistance=Math.sqrt(slTest+swTest+plTest+pwTest);
		               if (testDistance < distance) {
		                   rowNum = i;
		                   distance = testDistance;
		                   
		               }
		     }
		     return rowNum;
	}
	  //Calculating our closes training label from the given testing values
	private static String[] prediction(double testingVal[][],double trainingVal[][],String [] trainingClassLabel,String [] predictedClassLabel) {
	   for (int i = 0; i < 75; i++) {
	       int closestPrediction = leastdistance(testingVal[i][0], testingVal[i][1],testingVal[i][2], testingVal[i][3],trainingVal);
	       predictedClassLabel[i] = trainingClassLabel[closestPrediction];
	   }
	       return predictedClassLabel;
	}
	  
	  
	   //Finding accuracy of our correct values from our total values
	   public static void accuracy(String[]trueLabel, String[]predictedLabel) {
	           int count=0;
	           double correctVal = 0;
	           double accurate = 0;
	           System.out.println("\nEX#: TRUE LABEL, PREDICTED LABEL");
	           for (int i = 0; i < 75; i++) {
	               count++;
	               System.out.println(count+": "+trueLabel[i] + " " + predictedLabel[i]);
	               if (trueLabel[i].equals(predictedLabel[i])) {
	               correctVal++;
	               accurate=(correctVal/75);
	               }
	           }
	       System.out.print("ACCURACY: "+ accurate);
	   }
	  
	  
	   //Print out for our heading
	   public static String heading() {
	   String output=("");
	   System.out.println(output);
	   return output;
	   }
	}

