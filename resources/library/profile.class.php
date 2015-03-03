<?php

class Profile {

    private function __construct() {}
    
    public static function getProfileData($jsonObject, $serverConnection){
		$id = $jsonObject -> {"id"};
	
		$sqlInputOrder = "Select * FROM `profiletoinstrument`
		WHERE ProfileID = '$id'";
		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		print $sqlInputOrder;
		$i = 0;
		$instrumentValue[0] = "nothing";
		while($row = mysqli_fetch_object($sqlResult)){	
			$instrumentValue[$i] = $row -> Instrument;
			$i++;
		}	
				
		$sqlInputOrder = "Select * FROM `profile`
		WHERE ID = '$id' LIMIT 1";	
			print "\br";
			print $sqlInputOrder;
		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		while($row = mysqli_fetch_object($sqlResult)){	
			$value = array('command' => 'getprofiledata',
				'profileID'=> $id,
				'password'=> $row -> Password,
				'firstName'=> $row -> FirstName,
				'secondName' => $row -> SecondName,
				'mobileNumber' => $row -> MobileNumber,
				'email' => $row -> Email,
				'genre' => $row -> Genre,
				'adress' => $row -> Adress,
				'instrument' => $instrumentValue,
				'status'=>'true');
			
			$result = json_encode ($value);
			return $result;
		}
	return '{"command":"getprofiledata","status":"false"}';
			
	}
	public static function updateProfileData($jsonObject, $serverConnection){
		$id = $jsonObject -> {"id"};
		$firstName = $jsonObject -> {"firstName"};
		$secondName = $jsonObject -> {"secondName"};
		$mobileNumber = $jsonObject -> {"mobileNumber"};
		$email = $jsonObject -> {"email"};
		$instrument = $jsonObject -> {"instrument"};
		$genre = $jsonObject -> {"genre"};
		$adress = $jsonObject -> {"adress"};
		$status = "true";
		$instrumentStatus = $jsonObject -> {"instrumentStatus"};
		//band missing
		//picture missing
		
		if(Profile::checkEMail($email,$id,$serverConnection) == "emailAvailable"){
			$sqlInputOrder = "UPDATE profile
			SET FirstName='$firstName ',
				SecondName='$secondName',
				MobileNumber='$mobileNumber',
				Email='$email',
				Genre='$genre',
				Adress='$adress'
			WHERE ID='$id'"; 
			
			$sqlInput = mysqli_query($serverConnection,$sqlInputOrder);
				if($sqlInput == false){
				   return '{"command":"updateprofiledata","status":"error at sql input when updating profile table"}';
				}	
				
				if($instrumentStatus == "add"){
					$sqlInputOrder = "INSERT INTO profiletoinstrument
					(ProfileID,Instrument) 
					VALUES ('$id','$instrument')";
				}
				else if($instrumentStatus == "delete"){
					$sqlInputOrder = "DELETE FROM profiletoinstrument
					WHERE ProfileID= '$id' AND Instrument = '$instrument'; ";
				}
				
				print "</br>";
				print $sqlInputOrder;
				$sqlInput = mysqli_query($serverConnection,$sqlInputOrder);
				if($sqlInput == true){
				   return '{"command":"updateProfileData","status":"true"}';
				}
				else{
				   return '{"command":"updateProfileData","status":"error at sql input in band.class/insert into "bandtoinstrument table""}';
				}
			}
			return '{"command":"updateProfileData","status":"emailAlreadyExits"}';
	}
	public static function checkEMail($email,$id,$serverConnection){
		$sqlInputOrder = "Select * FROM `profile`
		WHERE Email = '$email' AND ID != '$id' LIMIT 1";
		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		while($row = mysqli_fetch_object($sqlResult)){
			if($row->Email == $email){
				return "false";
			}
		}
		return "emailAvailable";
	}

}
?>

