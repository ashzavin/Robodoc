<?php
if(true){
$username = $_POST['username'];
$password = $_POST['password'];
require "dbConnect.php";

$sql = "SELECT * FROM Users where username='$username' AND password='$password' ";
$result = $con->query($sql);

if($result->num_rows > 0)
{
echo "success";
}else{
echo "Invalid Username or Password";
}
}
else
{
	echo "nonpost";
}
