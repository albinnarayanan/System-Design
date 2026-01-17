package com.lld_ElevatorSystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Elevator {
	private int id;
	private int currFloor;
	private Direction direction;
	private  ElevatorState elevatorState;
	private List<ElevatorObserver> observers;
	private Queue<ElevatorRequest> requests;
	public Elevator(int id) {
		
		this.id = id;
		this.currFloor = 1;
		this.direction = Direction.IDLE;
		this.elevatorState = ElevatorState.IDLE;
		this.observers = new ArrayList<>();
		this.requests = new LinkedList<>();
	}
	public void addElevatorObserver(ElevatorObserver observer) {
		observers.add(observer);
	}
	public void removeElevatorObserver(ElevatorObserver observer) {
		observers.remove(observer);
	}
	private void notifyStateChange(ElevatorState state) {
		for(ElevatorObserver observer: observers) {
			observer.onElevatorStateChange(this,state);
		}
	}
	private void notifyFloorChange(int floor) {
		for(ElevatorObserver observer: observers) {
			observer.onElevatorFloorChange(this,floor);
		}
	}
	public void addRequest(ElevatorRequest request) {
		if(!requests.contains(request)) {
			requests.offer(request);
		}
		int requestedFloor = request.getFloor();
		if(elevatorState==ElevatorState.IDLE && !requests.isEmpty()) {
			if(requestedFloor>currFloor) {
				direction = Direction.UP;
			}
			else if(requestedFloor<currFloor) {
				direction = Direction.DOWN;
			}
			setElevatorState(ElevatorState.MOVING);
			
		}
	}
	public void moveToNextStop(int nextStop) {
		if(elevatorState!=ElevatorState.MOVING) {
			return;
		}
		while(currFloor!=nextStop) {
			if(direction==Direction.UP) {
				currFloor++;
			}
			else {
				currFloor--;
			}
			notifyFloorChange(currFloor);
			if(currFloor==nextStop) {
				completeArrival();
				return;
			}
		}
	
	}
	public void completeArrival() {
		setElevatorState(ElevatorState.STOPPED);
		requests.removeIf(request->request.getFloor() == currFloor);
		if(requests.isEmpty()) {
			direction = Direction.IDLE;
			setElevatorState(ElevatorState.IDLE);
			
		}
		else {
			setElevatorState(ElevatorState.MOVING);
		}
	}
	
	public int getId() {
		return id;
	}
	public int getCurrFloor() {
		return currFloor;
	}
	public Direction getDirection() {
		return direction;
	}
	public ElevatorState getElevatorState() {
		return elevatorState;
	}
	public List<ElevatorRequest> getDestinationFloors() {
		return new ArrayList<>(requests);
	}
	public Queue<ElevatorRequest> getRequestsQueue() {
		return new LinkedList<ElevatorRequest>(requests);
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public void setElevatorState(ElevatorState elevatorState) {
		this.elevatorState = elevatorState;
		notifyStateChange(elevatorState);
	}
	
	
	
	
	
}
