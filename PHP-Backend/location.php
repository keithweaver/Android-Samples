<?php
	$con=mysqli_connect("localhost","android_usr","Cheese54","androidDB");
	$latitude = $_POST['latitude'];
	$longitude = $_POST['longitude'];
	if($latitude != "" || $longitude != ""){
		//mysqli_query($con, "INSERT INTO locationTest (lat,lo) VALUES ('$latitude','$longitude')") or die("Failedd");
		mysqli_query($con, "INSERT INTO locationTest (lat,lo) VALUES ('$latitude','$longitude')") or die("Failedd");
		
		echo 'Success';
	}else{
		echo 'Failed';
	}
	mysqli_close($con);
?>