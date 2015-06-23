<?php
	$con = mysqli_connect("localhost", "android_usr","Cheese54","androidDB") or die("Unable to connect");
	/*
	$q = mysqli_query($con, "SELECT * FROM tableTest WHERE birthyear>'" . $_REQUEST['year'] . "'");
	while($e = mysqli_fetch_assoc($q)){
		$output[]=$e;
	}
	print(json_encode($output));
	mysqli_close();
	*/
	//id
	//name
	//sex
	//birthyear
	$username = $_POST['username'];
	$password = $_POST['password'];
	$result = mysqli_query($con,"SELECT Role FROM table1 where Username='$username' and Password='$password'");
	$row = mysqli_fetch_array($result);
	$data = $row[0];

	if($data){
		echo $data;
	}
	mysqli_close($con);
?>