package com.lld_ElevatorSystem;

public class Building {
	private String name;
	private int noOfFloors;
	private ElevatorController elevatorController;
	
	public Building(String name, int noOfFloors, int noOfElevators) {
		this.name = name;
		this.noOfFloors = noOfFloors;
		this.elevatorController = new ElevatorController(noOfElevators);
		
	}

	public String getName() {
		return name;
	}

	public int getNoOfFloors() {
		return noOfFloors;
	}

	public ElevatorController getElevatorController() {
		return elevatorController;
	}

	
	

}
