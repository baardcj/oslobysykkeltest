package app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StationStatus{
	
	private int station_id; 
	private int is_installed;
	private int is_renting;
	private int is_returning;
	private int last_reported;
	private int num_bikes_available;
	private int num_docks_available;
	
}
