<?php
  
  /**
 * Created by Moses Mwongela
 * on 7/16/15
 * moses1889@gmail.com
 *
 * Reference: http://www.androidhive.info/2012/10/android-push-notifications-using-google-cloud-messaging-gcm-php-and-mysql/
 */
  
class DB_Connect {
  
    function __construct() {
  
    }
   
    function __destruct() {
        // $this->close();
    }
   
    public function connect() {
        require_once 'config.php'; 
        $con = mysql_connect(DB_HOST, DB_USER, DB_PASSWORD); 
        mysql_select_db(DB_DATABASE); 
        return $con;
    }
   
    public function close() {
        mysql_close();
    }
  
} 
?>