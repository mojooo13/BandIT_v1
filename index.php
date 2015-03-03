<?php
require_once(realpath(dirname(__FILE__) . '/resources/config.inc.php'));


require_once(LIBRARY_PATH . '/signup.class.php');//benötigt um auf alles in signup.class zugreifen zu können
require_once(LIBRARY_PATH . '/login.class.php');
require_once(LIBRARY_PATH . '/profile.class.php');
require_once(LIBRARY_PATH . '/band.class.php');
require_once(LIBRARY_PATH . '/event.class.php');
require_once(LIBRARY_PATH . '/notification.class.php');
require_once(LIBRARY_PATH . '/search.class.php');
	
	if(isset($_GET['json'])){
		$jsonString = $_GET['json'];
	
	
		$jsonStringAnswer = '{"command":"Error in index or no method called or executed correctly"}';
		print "Input:";
		print $jsonString;
		print "<br>";

		$jsonObject = json_decode($jsonString);
		$method = $jsonObject ->{"command"};

			$serverConnection = mysqli_connect("localhost", "Simon", "","simon");
				if(!$serverConnection)
				{
				  exit("Verbindungsfehler in signup.class: ".mysqli_connect_error());
				  return '{"command":"createAccount","status":"error at sql connecting"}';
				}
			
			print "command:";
			print $method;
			switch($method){
				case "searchDataList":
					$jsonStringAnswer = Search::getSearchResult($jsonObject,$serverConnection);
					break;
				case "getAllData":
					$jsonStringAnswer = Profile::getProfileData($jsonObject, $serverConnection)."%".
										Band::getBandList($jsonObject,$serverConnection)."&".
										Event::getEventList($jsonObject,$serverConnection);
										"!".Notification::getNotification($jsonObject, $serverConnection);
					break;
				case "createAccount":
					$jsonStringAnswer = SignUp::storeData($jsonObject, $serverConnection);
					break;
				case "login":
					$jsonStringAnswer = LogIn::checkLogInData($jsonObject, $serverConnection);
					print $jsonStringAnswer;
					$jsonObject = json_decode($jsonStringAnswer);
					
					if($jsonObject ->{"status"} == "true"){

						$jsonStringAnswer = Profile::getProfileData($jsonObject, $serverConnection)."%".
											Band::getBandList($jsonObject,$serverConnection)."&".
											Event::getEventList($jsonObject,$serverConnection)."!".
											Notification::getNotification($jsonObject, $serverConnection);
											
					}
					else{
						$jsonStringAnswer = $jsonStringAnswer."%"."&"."!";
					}
					break;
				case "getProfileData":
					$jsonStringAnswer = Profile::getProfileData($jsonObject, $serverConnection);
					break;
				case "updateProfileData":
					$jsonStringAnswer = Profile::updateProfileData($jsonObject, $serverConnection);
					break;
				case "createBand":
					$jsonStringAnswer = Band::createBand($jsonObject,$serverConnection);
					break;
				case "getBandList":
					$jsonStringAnswer = Band::getBandList($jsonObject,$serverConnection);
					break;
				case "getBandData":
					$jsonStringAnswer = Band::getBandData($jsonObject,$serverConnection);
					break;
				case "updateBandMember":
					$jsonStringAnswer = Band::updateBandMember($jsonObject,$serverConnection);
					break;
				case "createEvent":
					$jsonStringAnswer = Event::createEvent($jsonObject,$serverConnection);
					break;
				case "getEventData":
					$jsonStringAnswer = Event::getEventData($jsonObject,$serverConnection);
					break;
				case "addBandToEventRequest":
					$jsonStringAnswer = Event::addBandToEventRequest($jsonObject,$serverConnection);
					break;
				case "getEventList":
					$jsonStringAnswer = Event::getEventList($jsonObject,$serverConnection);
					break;
				case "getAvailableBandList":
					$jsonStringAnswer = Event::getAvailableBandList($jsonObject,$serverConnection);
					break;
				case "getNotification":
					$jsonStringAnswer = Notification::getNotification($jsonObject,$serverConnection);
					break;
				}
				
			
			mysqli_close($serverConnection);
			print "</br>";
			print "Antwort$";
			print $jsonStringAnswer;
		}
		else{
			print "Hello";
		}

?>


