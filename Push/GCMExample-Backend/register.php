<?php
	include_once('./include/conn.php');
	
	$regId = $_POST['regid'];
	$username = $_POST['username'];

	if($regId != ""){
		if($username != ""){
			$result = mysqli_query($con, "SELECT * FROM gcmusr1 WHERE usr='$username'") or die("Failed 003");
			if(mysqli_num_rows($result) == 0){
				mysqli_query($con, "INSERT INTO gcmusr1 (regid, usr, created_at) VALUES ('$regId','$username',NOW())") or die("Failed 004");
				echo 'Success';
			}else{
				echo 'Failed 004';
			}
		}else{
			echo 'Failed 002';
		}
	}else{
		echo 'Failed 001';
	}
?>