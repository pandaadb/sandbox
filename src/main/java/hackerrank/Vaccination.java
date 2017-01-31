package hackerrank;

import java.util.Scanner;

public class Vaccination {

	public static void main(String[] args) {
		vaccinate(new String[] {"2", "7", "200000", "500000"});
	}
	
	public static void vaccinate(String[] args) {
		// read input and calculate the total number of people
		Scanner s = new Scanner(System.in);
		
		int numberOfCities = s.nextInt();
		int numberOfClinics = s.nextInt();
		double totalPopulation = 0;
		double[] populations = new double[numberOfCities];
		for(int i = 0; i < numberOfCities; i++ ) {
			populations[i] = s.nextDouble();
			totalPopulation += populations[i];
		}
		System.out.println(totalPopulation);
		System.out.println(numberOfCities);
		System.out.println(numberOfClinics);
		
		// calculate percentage of people in city relative to total amount of people
		long peoplePerClinic = 0;
		for(int i=0; i< populations.length; i++) {
			// percentage of people in a city
			double percentage = (populations[i] / totalPopulation);
			System.out.println(percentage);
			// round number of clinics based on that percentage
			long clincsInCity = Math.round(numberOfClinics * percentage);
			// people each clinic needs to check
			long tmpPeoplePerClinic = Math.round(populations[i] / clincsInCity);
			if(peoplePerClinic < tmpPeoplePerClinic ) {
				peoplePerClinic = tmpPeoplePerClinic;
			}
		}
		System.out.println(peoplePerClinic);
	}
	
}
