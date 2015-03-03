<?php

class SignUp {

    private function __construct() {}
    
    public static function storeData($jsonObject,$serverConnection){
	
		$firstName = $jsonObject ->{"firstName"};
		$secondName = $jsonObject ->{"secondName"};
		$email = $jsonObject ->{"email"};
		$password = $jsonObject -> {"password"};

		if(SignUp::checkEMail($email,$serverConnection) == "emailAvailable"){
		
			$sqlInputOrder = "INSERT INTO profile
			(FirstName,SecondName,Email,Password) 
			VALUES ('$firstName', '$secondName','$email','$password')";
			
			$sqlInput = mysqli_query($serverConnection,$sqlInputOrder);
			if($sqlInput == true){
			   return '{"command":"createAccount","status":"true"}';
			}
			else{
			   return '{"command":"createAccount","status":"error at sql input"}';
			}
		}
		else{
			return '{"command":"createAccount","status":"emailexists"}';
		}
	}
		
	public static function checkEMail($email,$serverConnection){
		$sqlInputOrder = "Select Email FROM `profile`
		WHERE Email = '$email' LIMIT 1";
		
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
