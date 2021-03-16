<?php
if(isset($_POST['Username']) && isset($_POST['Password'])){
    require_once 'conn.php';
    require_once 'validate.php';
    $Username = validate($_POST['Username']);
    $Password = validate($_POST['Password']);
    $sql = "select * from users where Username='$Username' and Password='$Password'";
    $result = $conn->query($sql);
    if($result ->num_rows > 0){
        echo 'success';
    }else{
        echo 'failure';
    }
}
?>