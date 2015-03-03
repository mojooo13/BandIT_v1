<?php

// defined('_HRM') or die;

// http://net.tutsplus.com/tutorials/php/organize-your-next-php-project-the-right-way/

$config = array(
    "db" => array(
        "dbname"    => "HamRelayMap",
        "username"  => "HamRelayMap",
        "password"  => "",
        "host"      => "localhost",
        "tblprefix" => "hrm_"
    ),
    "urls" => array(
            "baseUrl" => "http://localhost/HamRelayMap"
    ),
/*    "paths" => array(
            "resources" => "/path/to/resources",
            "images" => array(
                    "content" => $_SERVER["DOCUMENT_ROOT"] . "/images/content",
                    "layout" => $_SERVER["DOCUMENT_ROOT"] . "/images/layout"
            )
    )*/
);

/*
	I will usually place the following in a bootstrap file or some type of environment
	setup file (code that is run at the start of every page request), but they work 
	just as well in your config file if it's in php (some alternatives to php are xml or ini files).
*/

/*
	Creating constants for heavily used paths makes things a lot easier.
	ex. require_once(LIBRARY_PATH . "Paginator.php")
*/

//defined("BASE_PATH")
//	or define("BASE_PATH", realpath(dirname(__FILE__) . '/../'));

defined("LIBRARY_PATH")
	or define("LIBRARY_PATH", realpath(dirname(__FILE__) . '/library'));


/*
	Error reporting.
*/
ini_set("error_reporting", "true");
error_reporting(E_ALL | E_STRICT);

?>
