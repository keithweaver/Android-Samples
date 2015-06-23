<?php
	$con = mysqli_connect("localhost", "android_usr","Cheese54","androidDB") or die("Unable to connect");
	$q = mysqli_query($con, "SELECT * FROM tableTest WHERE birthyear>'" . $_REQUEST['year'] . "'");
	while($e = mysqli_fetch_assoc($q)){
		$output[]=$e;
	}
	print(json_encode($output));
	mysqli_close();
	//id
	//name
	//sex
	//birthyear
?>