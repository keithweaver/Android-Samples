<?php

	include_once('./include/conn.php');
	$username = $_POST['username'];
	$message = $_POST['message'];
	if($username != ""){
		if($message != ""){
			$r = mysqli_query($con, "SELECT * FROM gcmusr1 WHERE usr='$username'") or die("Failed 003");
			if(mysqli_num_rows($r) == 1){

				$regId = "";
				while($row = mysqli_fetch_array($r)){
					$regId = $row['regid'];
				}
				if($regId != ""){
					include_once './gcm.php';
				     
				    $gcm = new GCM();


				   $code = genCode();

				   mysqli_query($con, "INSERT INTO msg1 (code, msg) VALUES ('$code','$message')") or die("Failed 006");

				   $message = $code . "|" . $message;
				 
				    $registatoin_ids = array($regId);
				    $message = array("message" => $message);
				 
				    $result = $gcm->send_notification($registatoin_ids, $message);
				    
				    

				    echo $result;
				}else{
					echo 'Failed 005';
				}
			}else{
				echo 'Failed 004';
			}
		}else{
			echo 'Failed 002';
		}
	}else{
		echo 'Failed 001';
	}

	function genCode(){
		$con = mysqli_connect("YOUR_IP_OR_LOCALHOST","YOUR_DB_USER","YOUR_DB_PASSWORD","YOUR_DB") or die("Failed: unable to connect");
		$found = false;
		$s = "";
		$a = array("a","b","c","d","e","f","g","h","i","j","k");
		while($found != true){
			for($i = 0;$i < 15;$i++){
				$randNum = rand(0,10);
				$s .= $a[$randNum];
			}
			$r2 = mysqli_query($con, "SELECT * FROM msg1 WHERE code='$s'") or die("Failed 004");
			if(mysqli_num_rows($r2) == 0){
				$found = true;
				return $s;
			}else{
				$s = "";
			}
		}
		return "";
	}
?>