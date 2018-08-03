<?php

if($_SERVER['REQUEST_METHOD']=='POST'){ 
require "dbConnect.php";

$name = $_POST['name'];
$username = $_POST['username'];
$password = $_POST['password'];
$email = $_POST['email'];

$sql = "SELECT * FROM USERS WHERE username ='$username' OR email =' $email'";
if(isset($check)){
echo 'username or email already exist';
}
else{
$sql = "INSERT INTO Users (name,username,password,email) VALUES('$name','$username','$password','$email')";

if(mysqli_query($con, $sql))
{
	echo "successfully registered";
}
else
{
	echo "oops! Please try again!";
}

}
}
else
{
	echo "nonpost";
}
