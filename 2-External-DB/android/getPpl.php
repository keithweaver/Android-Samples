<?php
	$con = mysqli_connect("localhost","root","root","androidDB") or die("Unable to connect");
	$result = mysqli_query($con, "SELECT * FROM ppl") or die("Unable to get people info");
	while($row = mysqli_fetch_array($result)){
		$output[] = $row;
	}
	print(json_encode($output));
	mysqli_close($con);
?>