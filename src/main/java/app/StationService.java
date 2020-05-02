package app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import app.model.Station;
import app.model.StationStatus;
import app.modelwrappers.WrapDataStationInfo;
import app.modelwrappers.WrapDataStationStatus;

@EnableScheduling
@Service
public class StationService {
	
	private static final Logger log = LoggerFactory.getLogger(StationService.class);

	private final String stationInfoURL = "https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json";
	private final String stationStatusURL = "https://gbfs.urbansharing.com/oslobysykkel.no/station_status.json";
    
	private final Map<Integer, Station> stationMap = new HashMap<Integer, Station>();
	private final RestTemplate restTemplate = new RestTemplate();

	
	/*
	 *  stationMap keeps data of each station which are obtained from OsloBysykkel 
	 */
    @Bean
    public Map<Integer, Station> stationMap(){
    	return stationMap;
    }
    
    
    /*
     * updates available bikes and docks on OsloBysykkelstations
     */
    @Scheduled(fixedRate=10000)
    private void updateStationStatus() {
    	
    	if(stationMap.size() != 0){
    		log.info("UPDATING (station status)");
    	
    		try{
    			ResponseEntity<WrapDataStationStatus> statStatus = restTemplate.getForEntity(stationStatusURL, WrapDataStationStatus.class);
    			log.info(statStatus.getStatusCode().toString() + " (updateStationStatus)" ); 
    			
    			try {
    	    		List<StationStatus> statStatusList = statStatus.getBody().getData().getStations();
    	    		
    	    		for(StationStatus stStat : statStatusList) {
    	    			Station st = stationMap.get(stStat.getStation_id());
    	    			st.setNum_bikes_available(stStat.getNum_bikes_available());
    	    			st.setNum_docks_available(stStat.getNum_docks_available());
    	    		}
    				
            	} catch (NullPointerException e){
            		log.error("NullPointerException");
            	}
    		
    		} catch (RestClientResponseException e) {     	
            	log.error("RestClientResponseException");
            }
    	} else {
    		log.warn("NOT UPDATING (empty stationMap)");
    	}
    }
	
    
    /*
     * Initiation method that runs one queries OsloBysykkel to get info on all stations 
     */
    @PostConstruct
    public void init() {
    	
        log.info("INITIALISING");
        
        try {
        	ResponseEntity<WrapDataStationInfo> statInfo = restTemplate.getForEntity(stationInfoURL, WrapDataStationInfo.class);
        	log.info(statInfo.getStatusCode().toString() + " (init)" ); 
        	
        	try {
        		List<Station> statInfoList = statInfo.getBody().getData().getStations();
		
        		for(Station st : statInfoList) {
        			stationMap.put(st.getStation_id(), st);
        		}
   
        		updateStationStatus();
        		
        	} catch (NullPointerException e){
        		log.error("NullPointerException");
        	}
        } catch (RestClientResponseException e) {     	
        	log.error("RestClientResponseException");
        }
		
    }
	
}
