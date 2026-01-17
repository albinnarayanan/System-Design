package com.lld_ElevatorSystem;

public class ElevatorRequest {
	private int elevatorId;
	private Direction requestDirection;
	//from which floor req came
	private int floor;
	
	//from inside or outside
	private boolean isInternalReq;
	

	public ElevatorRequest(int elevatorId, Direction requestDirection, int floor, boolean isInternalReq) {
		this.elevatorId = elevatorId;
		this.requestDirection = requestDirection;
		this.floor = floor;
		this.isInternalReq = isInternalReq;
		
	}


	public int getElevatorId() {
		return elevatorId;
	}


	public Direction getRequestDirection() {
		return requestDirection;
	}


	public int getFloor() {
		return floor;
	}


	public boolean isInternalReq() {
		return isInternalReq;
	}
	
	
	
}
