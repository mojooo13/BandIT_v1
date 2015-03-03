<?php

class Search {

    private function __construct() {}
    
	public static function getSearchResult($jsonObject,$serverConnection){
	
		$searchType = $jsonObject -> {"type"};
		$answer[0] = "";
		switch($searchType){
			case "profile":
				$answer = Search::getProfileResult($jsonObject,$serverConnection); 
				break;
			case "band":
				$answer = Search::getBandResult($jsonObject,$serverConnection);
				break;
			case "event":
				$answer = Search::getEventResult($jsonObject,$serverConnection);
				break;
			case "music":
				break;
		}
		$value = array('command' => "searchDataList",
				'list' => $answer,
				'status' => 'true');
		
		if($answer[0] == "noResult")
			return '{"command":"searchDataList","status":"NoResults"}';
		$result = json_encode ($value);
		return $result;
		
	}
	
	public static function getProfileResult($jsonObject,$serverConnection){
		$name = $jsonObject -> {"name"};
		$genre = $jsonObject -> {"genre"};
		$value[0] = "noResult";
		
		$names = preg_split("#\s#", $name);

			$sqlInputOrder = "Select FirstName,SecondName,ID,Genre FROM `profile` WHERE ( ";
			
			for($i = 0;$i < count($names);$i ++) {
				if($i != 0)
					$sqlInputOrder .= " OR ";
				
				$sqlInputOrder .= "(FirstName LIKE '%$names[$i]%' OR SecondName LIKE '%$names[$i]%')";
			}
			$sqlInputOrder .= " ) ";
			
			if($genre !== "")
				$sqlInputOrder .= "AND Genre LIKE '$genre'";

		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		$i = 0;
		while($row = mysqli_fetch_object($sqlResult)){	
			$firstName = $row -> FirstName;
			$secondName = $row -> SecondName;
			$name = $firstName." ".$secondName;
			
			$value[$i] = array('name' => $name,
				'genre' => $row -> Genre,
				'id' => $row -> ID);
			$i++;
		}
		return $value;
		
	}
	
	public static function getBandResult($jsonObject,$serverConnection){
		$name = $jsonObject -> {"name"};
		$genre = $jsonObject -> {"genre"};
		$value[0] = "noResult";
		
		$sqlInputOrder = "Select * FROM `band` WHERE	
						Bandname LIKE '%$name%'";
		
		if($genre != null)
			$sqlInputOrder = $sqlInputOrder." AND BandGenre LIKE '%$genre%'";
			
		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		
		$i = 0;
		while($row = mysqli_fetch_object($sqlResult)){	
			$value[$i] = array('name' => $row -> BandName,
				'genre' => $row -> BandGenre,
				'id' => $row -> ID);
			$i++;
		}
		return $value;
	}
	
	public static function getEventResult($jsonObject,$serverConnection){
		$name = $jsonObject -> {"name"};
		$genre = $jsonObject -> {"genre"};
		$value[0] = "noResult";
		$sqlInputOrder = "Select ID,EventName,EventGenre FROM `event` WHERE
						EventName LIKE '%$name%'";
						
		if($genre != null)
			$sqlInputOrder = $sqlInputOrder." AND EventGenre LIKE '%$genre%'";
			
		$sqlInputOrder = $sqlInputOrder." ORDER BY EventDate DESC";
		
		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		$i = 0;
		while($row = mysqli_fetch_object($sqlResult)){	
			$value[$i] = array('name' => $row -> EventName,
				'genre' => $row -> EventGenre,
				'id' => $row -> ID);
			$i++;
		}
		return $value;
	}
}
?>


