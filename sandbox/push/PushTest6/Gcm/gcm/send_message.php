<?php

/**
 * Created by Moses Mwongela
 * on 7/16/15
 * moses1889@gmail.com
 *
 * Reference: http://www.androidhive.info/2012/10/android-push-notifications-using-google-cloud-messaging-gcm-php-and-mysql/
 */

if (isset($_GET["regId"]) && isset($_GET["message"])) {
    $regId = $_GET["regId"];
    $message = $_GET["message"];
     
    include_once './gcm.php';
     
    $gcm = new GCM();
 
    $registatoin_ids = array($regId);
    $message = array("message" => $message);
 
    $result = $gcm->send_notification($registatoin_ids, $message);
 
    echo $result;
 
}
?>