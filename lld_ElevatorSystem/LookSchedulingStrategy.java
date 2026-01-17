package com.lld_ElevatorSystem;

import java.util.Queue;

public class LookSchedulingStrategy  implements SchedulingStrategy{

	@Override
	public int getNextStop(Elevator elevator) {
		int currFloor = elevator.getCurrFloor();
		Queue<ElevatorRequest> requests = elevator.getRequestsQueue();
		if(requests==null || requests.isEmpty()) {
			return currFloor;
		}
		ElevatorRequest primaryRequest = requests.peek();
		int primaryFloor = primaryRequest.getFloor();
		Direction travelDirection;
		if(primaryFloor>currFloor) {
			travelDirection = Direction.UP;
			
		}
		else if(primaryFloor<currFloor) {
			travelDirection = Direction.DOWN;
		}
		else {
			return currFloor;
		}
		
		Integer candidate = null;
		
		for(ElevatorRequest req : requests) {
			int reqFloor = req.getFloor();
			if(travelDirection==Direction.UP && reqFloor>currFloor && reqFloor<=primaryFloor) {
				if(req.isInternalReq() ||(!req.isInternalReq() && req.getRequestDirection()==Direction.UP)) {
					if(candidate==null || candidate>reqFloor) {
						candidate = reqFloor;
					}
				}
			}
			else if(travelDirection==Direction.DOWN && reqFloor<currFloor && reqFloor>=primaryFloor) {
				if(req.isInternalReq() || (!req.isInternalReq() && req.getRequestDirection()==Direction.DOWN)) {
					if(candidate==null || candidate<reqFloor) {
						candidate = reqFloor;
						
					}
				}
			}
		}
		
		return candidate!=null?candidate:primaryFloor;
	}

}
