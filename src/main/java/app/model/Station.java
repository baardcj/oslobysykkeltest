package app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;


/*
 * Model of station 
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {
	
	private int station_id; 
	private String name;
	private String address;
	private float lat; 
	private float lon; 
	private int capacity;
	
	private int num_bikes_available;
	private int num_docks_available;
	
}
