<?php
if(isset ($_POST['Username']) && isset($_POST['Password']) && isset($_POST['Email'])){
    require_once 'conn.php';
    require_once 'validate.php';
    $Username = $_POST['Username'];
    $Password = $_POST['Password'];
    $Email = $_POST['Email'];
    $sql = "insert into users values('$Username', '$Password', '$Email')";
    if(!$conn->query($sql)){
        echo "failure";
    }else{
        echo "success";
    }
}
?>
