package com.lld_ElevatorSystem;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ElevatorMain {

	public static void main(String[] args) {
		Building building = new Building("Office Tower", 10, 3);
		ElevatorController controller = building .getElevatorController();
		ElevatorDisplay display = new ElevatorDisplay();
		for(Elevator elevator : controller.getElevators()) {
			elevator.addElevatorObserver(display);
		}
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		System.out.println("Elevator System Simulation");
		System.out.println("Building: "+building.getName());
		System.out.println("No of floors: "+building.getNoOfFloors());
		System.out.println("No of elevators: "+controller.getElevators().size());
		while(running) {
			System.out.println("Select an option");
			System.out.println("1. Request elevator(External)");
			System.out.println("2. Request Floor(Internal");
			System.out.println("3. Simulate next step");
			System.out.println("4. Exit Simulation");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Enter elevator Id: ");
				int externalElevatorId = sc.nextInt();
				controller.setCurrElevatorId(externalElevatorId);
				System.out.println("Enter current floor number: ");
				int floorNum = sc.nextInt();
				System.out.println("Direction (1 for UP , 2 for DOWN)");
				int dirChoice = sc.nextInt();
				Direction dir = dirChoice==1?Direction.UP:Direction.DOWN;
				controller.requestElevator(externalElevatorId, floorNum, dir);
				break;
				
			case 2:
				System.out.println("Enter elevator Id: ");
				int elevatorId = sc.nextInt();
				controller.setCurrElevatorId(elevatorId);
				System.out.println("Enter destination floor: ");
				int destFloor = sc.nextInt();
				controller.requestFloor(elevatorId, destFloor);
				break;
				
				
			case 3:
				System.out.println("Simulating next step.....");
				controller.step();
				displayElevatorStatus(controller.getElevators());
				break;
		
			case 4:
				running = false;
				sc.close();
				break;
			
			default:
				System.out.println("Invalid choice");
			}
			
		
				
			
		}
		
	}
	public static void displayElevatorStatus(List<Elevator>elevators) {
		System.out.println("Elevator Status: ");
		for(Elevator elevator : elevators) {
			String floors = elevator.getDestinationFloors().stream()
				    .map(a -> String.valueOf(a.getFloor()))
				    .collect(Collectors.joining(", "));
			System.out.println("Elevator: "+elevator.getId()+", Floor: "+elevator.getCurrFloor()+
					 		", Direction: "+elevator.getDirection()+", State: "+elevator.getElevatorState()+
					 			", Destinations: "+floors);
		}
	}

}
