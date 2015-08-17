<?php
 
 /**
 * Created by Moses Mwongela
 * on 7/16/15
 * moses1889@gmail.com
 *
 * Reference: http://www.androidhive.info/2012/10/android-push-notifications-using-google-cloud-messaging-gcm-php-and-mysql/
 */
 
class DB_Functions {
 
    private $db;
  
    function __construct() {
        include_once './db_connect.php'; 
        $this->db = new DB_Connect();
        $this->db->connect();
    }
  
    function __destruct() {
         
    }
  
    public function storeUser($name, $email, $gcm_regid) { 
        $result = mysql_query("INSERT INTO gcm_users(name, email, gcm_regid, created_at) VALUES('$name', '$email', '$gcm_regid', NOW())"); 
        if ($result) { 
            $id = mysql_insert_id(); 
            $result = mysql_query("SELECT * FROM gcm_users WHERE id = $id") or die(mysql_error()); 
            if (mysql_num_rows($result) > 0) {
                return mysql_fetch_array($result);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
  
    public function getAllUsers() {
        $result = mysql_query("select * FROM gcm_users");
        return $result;
    }
 
}
 
?>