<?php

class Event{
	private function __construct() {}
	public static function createEvent($jsonObject, $serverConnection){
		$eventName = $jsonObject ->{"eventName"};
		$eventDate = $jsonObject ->{"eventDate"};
		$eventLocation = $jsonObject ->{"eventLocation"};
		$eventGenre = $jsonObject ->{"eventGenre"};
		$eventTime = $jsonObject ->{"eventTime"};

		if(Event::checkEventName($eventName,$serverConnection) == "EventNameAvailable"){
			$sqlInputOrder = "INSERT INTO event
			(EventName,EventDate,EventLocation,EventGenre,EventTime) 
			VALUES ('$eventName', '$eventDate','$eventLocation','$eventGenre','$eventTime')";
			
			$sqlInput = mysqli_query($serverConnection,$sqlInputOrder);
			return '{"command":"createEvent","status":"true"}';
		}

		else{
			return '{"command":"createEvent","status":"eventName already exists"}';
		}
		
	}
	
		
	public static function checkEventName($eventName,$serverConnection){
	
		$sqlInputOrder = "Select EventName FROM `event` 
		WHERE EventName = '$eventName' LIMIT 1";
		
		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		while($row = mysqli_fetch_object($sqlResult)){
			if($row->EventName == $eventName){
				return "false";
			}
		}
		return "EventNameAvailable";
	}
	
	public static function addBandToEventRequest($jsonObject,$serverConnection){
		$profileId = $jsonObject ->{"profileId"};
		$bandId = $jsonObject ->{"bandId"};
		$date = $jsonObject ->{"date"};
		$text = $jsonObject -> {"text"};
		
		$sqlInputOrder = "INSERT INTO bandtoeventrequest
			(ProfileID,BandID,Date,Status,Text) 
			VALUES ('$profileId', '$bandId','$date','false','$text')";
				
		$sqlInput = mysqli_query($serverConnection,$sqlInputOrder);
		if($sqlInput == true){
			return '{"command":"createAccount","status":"true"}';
		}
		else{
			return '{"command":"createAccount","status":"error at sql input"}';
		}
	}
	
	
	public static function getAvailableBandList($jsonObject,$serverConnection){
		$profileId = $jsonObject ->{"profileId"};
		$sqlInputOrder = "Select * FROM `bandtoeventrequest`
		WHERE ProfileID = '$profileId' AND Status = 'true'";
		
		$smallValue[0] = -1;
		
		$i = 0;	
		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);	
		while($row = mysqli_fetch_object($sqlResult)){	
			$smallValue[$i] = $row -> BandID;
			$i++;
		}
		$value = array('command' => 'getAvailableBandList','Bands' => $smallValue,'status'=>'true');
		
		if($smallValue != -1){
			return json_encode ($value);;
		}
		else{
			return '{"command":"getAvailableBandList","status":"no available bands found"}';
		}
	}
	
	public static function getEventData($jsonObject, $serverConnection){
		$eventId = $jsonObject -> {"eventId"};
		$sqlInputOrder = "Select * FROM `bandtoevent` 
		WHERE EventID = '$eventId'";
		$smallValue[0] = null;
		$i = 0;	
		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);	
		while($row = mysqli_fetch_object($sqlResult)){	
			$smallValue[$i] = $row -> BandID;
			$i++;
		}
		
		$sqlInputOrder = "Select * FROM `event`
		WHERE ID = '$eventId' LIMIT 1";
		
		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);	
		while($row = mysqli_fetch_object($sqlResult)){	
			$value = array('command' => 'getEventData',
				'eventName' => $row -> EventName,
				'eventDate' => $row -> EventDate,
				'eventLocation' => $row -> EventLocation,
				'eventGenre' => $row -> EventGenre,
				'eventTime' => $row ->EventTime,
				'BandIds' => $smallValue,	
				'status'=>'true');
		}
		$result = json_encode ($value);
		return $result;
	}

	public static function getEventList($jsonObject, $serverConnection){
		$profileId = $jsonObject -> {"id"};
		$smallValue[0] = null;
		$sqlInputOrder = "Select event.EventName,event.ID,event.EventGenre FROM
						profiletoband JOIN bandtoevent JOIN event	
						ON profiletoband.BandID = bandtoevent.BandID
						AND bandtoevent.EventID = event.ID
						WHERE profiletoband.ProfileID = '$profileId'";
		
		$i = 0;	
		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);	
		while($row = mysqli_fetch_object($sqlResult)){	
			$smallValue[$i] = array('eventID' => $row -> ID,
				'eventName' => $row -> EventName,
				'eventGenre' => $row -> EventGenre);
				
			$i++;
		}
		$value = array('command' => 'getEventList',
			'events' => $smallValue,
			'status' => 'true');
			
		$result = json_encode ($value);
		return $result;
		
	}
}
?>
