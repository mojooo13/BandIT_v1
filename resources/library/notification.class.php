<?php

class Notification{

	private function __construct() {}

	public static function getNotification($jsonObject,$serverConnection){
		$profileId = $jsonObject -> {"id"};
		
		$eventValue = null;
		$bandValue = null;
		$sqlInputOrder = "Select * FROM `profiletoband` JOIN band ON BandID = band.ID
		WHERE ProfileID = '$profileId' AND Status = 'false'";	
		$SqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		$i = 0;
		while($row = mysqli_fetch_object($SqlResult)){
			$bandId = $row -> BandID;
			$tableId = $row -> ID;
			
			$bandName = Notification::getBandName($serverConnection,$bandId);
			$bandValue[$i] = array('bandName' => $bandName,
				'bandGenre' => $row -> BandGenre,
				'text' => $row -> Text,
				'bandId' => $row -> BandID,
				'requestId' => $tableId);
			$i++;	
		}
		//--------------------------------------------------------------------------
		$sqlInputOrder = "SELECT *,bandtoevent.Text AS RequiredText FROM bandtoevent JOIN profiletoband JOIN event JOIN band JOIN profile
						ON bandtoevent.BandID = profiletoband.BandID
						AND bandtoevent.EventID = event.ID
						AND bandtoevent.BandID = band.ID
						AND bandtoevent.ProfileID = profile.ID
						WHERE profiletoband.ProfileID = '$profileId' AND bandtoevent.Status = 'pending'";
						
		$SqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		$i = 0;
		while($row = mysqli_fetch_object($SqlResult)){
			$eventValue = array('requestId' => $row -> ID,
								'requestSenderId' => $row -> ProfileID,
								'requestFirstName' => $row -> FirstName,
								'requestSecondName' => $row -> SecondName,
								'bandname' => $row -> BandName,
								'eventID' => $row -> EventID,
								'eventName' => $row -> EventName,
								'eventGenre' => $row -> EventGenre,
								'eventLocation' => $row -> EventLocation,
								'eventDate' => $row -> EventDate,
								'eventTime' => $row -> EventTime,
								'text' => $row -> RequiredText);
		}
		$value = array('command' => "getNotification",
			'bandRequest' => $bandValue,
			'eventRequest' => $eventValue,
			'status' => "true");
			
		$result = json_encode ($value);
		return $result;
	}
	
	public static function getNameOfProfile($serverConnection,$profileId){
		$sqlInputOrder = "Select * FROM `profile`
		WHERE ID = '$profileId'";
		
		$firstSqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		while($row = mysqli_fetch_object($firstSqlResult)){
			$profileFirstName = $row -> FirstName;
			$profileSecondName = $row -> SecondName;
		}
		return $profileFirstName . $profileSecondName;
	
	}
	
	public static function getBandName($serverConnection,$bandId){
		$sqlInputOrder = "Select * FROM `band`
		WHERE ID = '$bandId'";	
		$SqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		while($row = mysqli_fetch_object($SqlResult)){
			$bandName = $row -> BandName;
		}
		return $bandName;
	}
	
	
}
?>
