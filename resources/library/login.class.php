<?php

class LogIn {

    private function __construct() {}
    
    public static function checkLogInData($jsonObject, $serverConnection){
		$email = $jsonObject ->{"email"};
		$password = $jsonObject ->{"password"};
	

		$sqlInputOrder = "Select * FROM `profile`
		WHERE Email = '$email' LIMIT 1";
		$sqlResult = mysqli_query($serverConnection,$sqlInputOrder);
		
		
		while($row = mysqli_fetch_object($sqlResult)){
			if($row->Password == $password){	
				$value = array('command' => 'login','id'=> $row -> ID,'status'=>'true');
				$result = json_encode ($value);
				return $result;
			}
			else{
				return '{"command":"login","id":"-1","status":"passwordwrong"}';
			}
			
		}
		return '{"command":"login","id":"-1","status":"emailnotfound"}';
	}
}
?>
