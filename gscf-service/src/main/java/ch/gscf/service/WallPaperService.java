package ch.gscf.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import ch.gscf.service.dto.Floor;

@Service
public class WallPaperService implements WallPaperAPI {
	private List<Room> rooms = new ArrayList<>();

	@PostConstruct
	private void buildRooms() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("input1.txt");
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split("x");
				Room room = new Room(Integer.valueOf(values[0]), Integer.valueOf(values[1]), Integer.valueOf(values[2]));
				rooms.add(room);
			}
		}
	}
	
	@Override
	public Floor getTotalSquareFeet() {
		Integer total = 0;
		for (Room r : rooms) {
			if (Boolean.FALSE.equals(r.isCube())) total += r.calculateArea();
			else total += r.calculateArea() + r.smallSideArea();
		}
		return new Floor(this.rooms.size(), total);
	}
	
	@Override
	public List<Floor> getCubicShape() {
		List<Floor> result = new ArrayList<>();
		for (int i=0; i < this.rooms.size(); i++) {
			if (Boolean.TRUE.equals(rooms.get(i).isCube())) 
				result.add(new Floor(i, rooms.get(i).calculateArea()));
		}
		return result.stream()
					 .sorted(Comparator.comparing(k -> ((Floor) k).getSquare()).reversed())
					 .collect(Collectors.toList());
	}

	@Override
	public List<Floor> sameAreaRooms() {
		Map<Integer, Integer> temp = new HashMap<>();
		List<Floor> result = new ArrayList<>();
		List<Integer> areas = this.rooms.stream().map(Room::calculateArea).collect(Collectors.toList());
		for (int i=0; i < areas.size(); i++) {
			Integer area = areas.get(i);
			for (int y=i+1; y < areas.size(); y++) {
				if (area.equals(areas.get(y)) && !temp.containsValue(area)) {
					temp.put(i, area);
					break;
				}
			}
		}
		
		temp.forEach((K, V) -> result.add(new Floor(K, V)));

		return result.stream()
				 .sorted(Comparator.comparing(Floor::getId))
				 .collect(Collectors.toList());
	}

}
