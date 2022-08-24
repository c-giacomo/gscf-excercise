package ch.gscf.web.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.gscf.service.WallPaperService;
import ch.gscf.service.dto.Floor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "GSCF Operation", description = "Implemented requested operation")
@RestController
@RequestMapping("/api/v1/")
public class GscfController {
	
	@Autowired
	WallPaperService wallPaperService;
	
	@Operation(summary = "Returns number of total square feet of wallpaper for all rooms.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Floor.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content)
	})
	@GetMapping("/calculateAllSquare")
	public ResponseEntity<Floor> calculateAllSquare() {
		Floor result = wallPaperService.getTotalSquareFeet();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "Returns all rooms that have a cubuc shape, with room number and square, in wallpaper descendind order")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success.", content = {
					@Content(array = @ArraySchema(schema = @Schema(implementation = Floor.class))) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content)
	})
	@GetMapping("/calculateCubicShape")
	public ResponseEntity<List<Floor>> calculateCubicShape() {
		List<Floor> result = wallPaperService.getCubicShape();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "Returns all rooms that are present more than once, with room number and square")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success.", content = {
					@Content(array = @ArraySchema(schema = @Schema(implementation = Floor.class))) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content)
	})
	@GetMapping("/findDuplicate")
	public ResponseEntity<List<Floor>> findDuplicate() {
		List<Floor> result = wallPaperService.sameAreaRooms();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
