 <?php  

 require "dbConnect.php";
 $user_name = $_POST["login_name"];  
 $user_pass =  $_POST["login_pass"];  
 $sql_query = "SELECT username FROM Users where username like '$user_name' and password like '$user_pass';";  
 $result = mysqli_query($con,$sql_query);  
 if(mysqli_num_rows($result) >0 )  
 {  
 $row = mysqli_fetch_assoc($result);  
 $name =$row["username"];  
	echo "Welcome";
 }  
 else  
 {    
 echo "Login Failed.......Try Again..";
 }  
 ?> 
