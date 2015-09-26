<?php
	
	$target_dir = "/testing_upload";
	mkdir($target_dir);
	$filename = str_replace(" ","",basename( $_FILES['imgFile']['name']) ) ;
	$target_path = $target_dir . "/" . $filename;
	if(move_uploaded_file($_FILES['imgFile']['tmp_name'], $target_path)) {
	} else{
	    die('{success: "NO -[' . $target_path .'] [' . $filename . '] - ' . $_FILES['imgFile']['tmp_name'] . '"}');
	}
	
	
	echo '{success: "Yes"}';
?>