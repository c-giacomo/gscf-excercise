package ch.gscf.service;

import java.util.Comparator;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Room {
	private final Integer length;
	private final Integer width;
	private final Integer height;
	
	public Boolean isCube() {
		return this.length.equals(width) && this.length.equals(height) && this.width.equals(height);
	}
	
	public Integer calculateArea() {
		return ( (2 * this.length * this.width) + (2 * this.width * this.height) + (2 * this.height * this.length) );
	}
	
	public Integer smallSideArea() {
		return Stream.of(this.length * this.width, this.width * this.height, this.height * this.length)
						.min(Comparator.comparing(Integer::valueOf)).get();
	}
}
