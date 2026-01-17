package com.lld_ElevatorSystem;

import java.util.PriorityQueue;
import java.util.Queue;

public class ScanSchedulingStrategy implements SchedulingStrategy {

	@Override
	public int getNextStop(Elevator elevator) {
		Direction elevatorDirection = elevator.getDirection();
		int currFloor = elevator.getCurrFloor();
		Queue<ElevatorRequest> requests = elevator.getRequestsQueue();
		
		if(requests.isEmpty()) {
			return currFloor;
		}
		PriorityQueue<ElevatorRequest> upQueue = new PriorityQueue<ElevatorRequest>();
		PriorityQueue<ElevatorRequest> downQueue = new PriorityQueue<ElevatorRequest>((a,b)->
													Integer.compare(a.getFloor(), b.getFloor()));
		
		while(!requests.isEmpty()) {
			ElevatorRequest request = requests.poll();
			int floor = request.getFloor();
			if(floor>currFloor) {
				upQueue.offer(request);
			}
			else {
				downQueue.offer(request);
			}
		}
		if(elevatorDirection== Direction.IDLE) {
			int nearestUpwardReq = upQueue.isEmpty()?-1:upQueue.peek().getFloor();
			int nearestDownwardReq = downQueue.isEmpty()?-1:downQueue.peek().getFloor();
			
			if(nearestUpwardReq ==-1) {
				elevator.setDirection(Direction.DOWN);
				return downQueue.poll().getFloor();
			}
			else if(nearestDownwardReq==-1) {
				elevator.setDirection(Direction.UP);
				return upQueue.poll().getFloor();
			}
			else {
				if(Math.abs(nearestUpwardReq-currFloor) < Math.abs(nearestDownwardReq)-currFloor) {
					elevator.setDirection(Direction.UP);
					return upQueue.poll().getFloor();
				}
				else {
					elevator.setDirection(Direction.DOWN);
					return downQueue.poll().getFloor();
					
				}
			}
		}
		if(elevatorDirection==Direction.UP) {
			return !upQueue.isEmpty()?upQueue.poll().getFloor():switchDirection(elevator,upQueue);
			
		}
		else {
			return !downQueue.isEmpty()?downQueue.poll().getFloor():switchDirection(elevator, downQueue);
		}
			
	
	}
	private int switchDirection(Elevator elevator, PriorityQueue<ElevatorRequest> requestsQueue) {
		elevator.setDirection(elevator.getDirection()==Direction.UP?Direction.DOWN:Direction.DOWN);
		return requestsQueue.isEmpty()?elevator.getCurrFloor():requestsQueue.poll().getFloor();
	
	}

}
