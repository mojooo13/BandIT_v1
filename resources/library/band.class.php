<?php

class Band {

    private function __construct() {}
    
    public static function createBand($jsonObject,$serverConnection){
		$bandName = $jsonObject ->{"bandName"};
		$genre = $jsonObject ->{"genre"};
		$profileId = $jsonObject ->{"id"};
		
		if(Band::checkBandName($bandName,$serverConnection) == "bandNameAvailable"){
			
			$sqlInputOrder = "INSERT INTO band
			(BandName,BandGenre) 
			VALUES ('$bandName', '$genre')";
			
			
			$sqlInput = mysqli_query($serverConnection,$sqlInputOrder);
			$bandId = mysqli_insert_id($serverConnection); 
			if($sqlInput == true){
			}
			else{
			   return '{"command":"createBand","status":"error at sql input in band.class/insert into "bandtable""}';
			}
			
			$sqlInputOrder = "INSERT INTO profiletoband
			(BandId,ProfileID,Status) 
			VALUES ('$bandId', '$profileId','true')";
			
			$sqlInput = mysqli_query($serverConnection,$sqlInputOrder);
			if($sqlInput == true){
			   return '{"command":"createBand","status":"true"}';
			}
			else{
			   return '{"command":"createBand","status":"error at sql input in band.class/insert into "bandtoprofiletable""}';
			}

		}
		else{
			return '{"command":"createBand","status":"bandnameexists"}';
		}
		
		
	}
	
	public static function getBandList($jsonObject,$serverConnection){
		$id = $jsonObject -> {"id"};
		
		$sqlInputOrder = "Select * FROM `profiletoband`
		WHERE ProfileID = '$id'";
			
		$firstSqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		
		$i = 0;
		while($firstRow = mysqli_fetch_object($firstSqlResult)){
		
			$bandId = $firstRow -> BandID;
			$sqlInputOrder = "Select * FROM `band`
			WHERE ID = '$bandId'";
			$secondSqlResult = mysqli_query($serverConnection,$sqlInputOrder);
			
			while($secondRow = mysqli_fetch_object($secondSqlResult)){	
				$partValue = array('bandName' => $secondRow -> BandName,
				'genre'=> $secondRow -> BandGenre,
				'id' => $secondRow -> ID);
			}	
			$completeValue[$i] = $partValue;
			$i++;
		}
		$value = array('command' => 'getBandList',
				'bands' => $completeValue,
				'status' => 'true');
		
		$result = json_encode ($value);
		return $result;
	}
	
	public static function getBandData($jsonObject,$serverConnection){
		$bandId = $jsonObject ->{"id"};
		
		$sqlInputOrder = "Select * FROM `profiletoband`
		WHERE BandID = '$bandId' AND Status = 'true'";	
		$firstSqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		
		$i = 0;
		while($firstRow = mysqli_fetch_object($firstSqlResult)){
			$profileId = $firstRow -> ProfileID;
			$sqlInputOrder = "Select * FROM `profile`
			WHERE ID = '$profileId'";	
			
			$secondSqlResult = mysqli_query($serverConnection,$sqlInputOrder);
			while($secondRow = mysqli_fetch_object($secondSqlResult)){
				$memberValue = array("profileMember" => ($secondRow -> FirstName)." ".$secondRow -> SecondName);
			}
			$completeValue[$i] = $memberValue;
			$i++;		
		}
		
		$sqlInputOrder = "Select * FROM band
		WHERE ID = '$bandId'";	
		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		while($row = mysqli_fetch_object($sqlResult)){
			$value = array("command" => "getBandData",
				"bandName" => $row -> BandName,
				"bandGenre" => $row -> BandGenre,
				"members" => $completeValue,
				"status" => "true");
		}
		$result = json_encode($value);
		return $result;
		
	}
	
	public static function updateBandMember($jsonObject,$serverConnection){
		$profileId = $jsonObject -> {"profileId"};
		$bandId = $jsonObject ->{"bandId"};
		$order = $jsonObject -> {"order"};

		if($order == "insert"){
			$sqlInputOrder = "INSERT INTO profiletoband 
			(ProfileID,BandID,Status) 
			VALUES ('$profileId', '$bandId','false')";
		}
		else if($order == "delete"){
			$sqlInputOrder = "DELETE FROM profiletoband
			WHERE ProfileID= '$profileId' AND BandId = '$bandId'; ";
		
		}
		else{
			return '{"command":"updateBandMember","status":"error unknow order nothing inserted or deleted"}';
		}
		$sqlInput = mysqli_query($serverConnection,$sqlInputOrder);
		if($sqlInput == true){
			return '{"command":"updateBandMember","status":"true"}';
		}
		else{
			return '{"command":"updateBandMember","status":"error at sql input"}';
		}
	}
	
	public static function checkBandName($bandName,$serverConnection){
		$sqlInputOrder = "Select BandName FROM `band`
		WHERE BandName = '$bandName' LIMIT 1";
		
		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		while($row = mysqli_fetch_object($sqlResult)){
			if($row->BandName == $bandName){
				return "false";
			}
		}
		return "bandNameAvailable";
	}
	
}
?>