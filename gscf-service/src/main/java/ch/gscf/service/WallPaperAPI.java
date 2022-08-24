package ch.gscf.service;

import java.util.List;

import ch.gscf.service.dto.Floor;

public interface WallPaperAPI {
	
	public Floor getTotalSquareFeet();
	
	public List<Floor> getCubicShape();
	
	public List<Floor> sameAreaRooms();

}
