package com.lld_ElevatorSystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
	private List<Elevator> elevators;
	private SchedulingStrategy schedulingStrategy;
	private int currElevatorId;
	
	public ElevatorController(int noOfElevators) {
		elevators = new ArrayList<Elevator>();
		for(int i=0; i<noOfElevators; i++) {
			elevators.add(new Elevator(i));
		}
		
		this.schedulingStrategy = new LookSchedulingStrategy();
		
	}
	public void requestElevator(int elevatorId, int floorNumber, Direction direction) {
		System.out.println("Exteral request: Floor "+floorNumber+",Direction "+direction);
		Elevator selectedElevator = getElevatorById(elevatorId);
		if(selectedElevator!=null) {
			selectedElevator.addRequest(new ElevatorRequest(elevatorId, direction, floorNumber, false));
			System.out.println("Assigned elevator: "+selectedElevator.getId()+ " to floor: "+floorNumber);
		}
		else {
			System.out.println("No elevator available for floor "+floorNumber);
		}
	}
	public void requestFloor(int elevatorId, int floorNo) {
		
		Elevator selectedElevator = getElevatorById(elevatorId);
		System.out.println("Internal request :Elevator " +elevatorId+ ", to Floor "+floorNo);
		Direction direction = floorNo>selectedElevator.getCurrFloor()?Direction.UP:Direction.DOWN;
		selectedElevator.addRequest(new ElevatorRequest(elevatorId, direction, floorNo, true));
	}

	public Elevator getElevatorById(int elevatorId) {
		for(Elevator elevator : elevators) {
			if(elevator.getId()==elevatorId) {
				return elevator;
			}
		}
		return null;
	}
	
	public void step() {
		for(Elevator elevator : elevators) {
			if(!elevator.getRequestsQueue().isEmpty()) {
				int nextStop = schedulingStrategy.getNextStop(elevator);
				
				if(elevator.getCurrFloor()!=nextStop) {
					elevator.moveToNextStop(nextStop);
				}
			}
		}
	}
	
	public List<Elevator> getElevators() {
		return elevators;
	}



	



	public SchedulingStrategy getSchedulingStrategy() {
		return schedulingStrategy;
	}



	public int getCurrElevatorId() {
		return currElevatorId;
	}
	public void setSchedulingStrategy(SchedulingStrategy schedulingStrategy) {
		this.schedulingStrategy = schedulingStrategy;
	}
	public void setCurrElevatorId(int currElevatorId) {
		this.currElevatorId = currElevatorId;
	}


	



	

}
