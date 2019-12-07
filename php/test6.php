

<?php
  $mysqli = new mysqli("localhost", "root", "1234", "arduino");
  $temperature = $_GET['temperature'];
  $humadity = $_GET['humadity'];
  $light = $_GET['light'];
  $sound = $_GET['sound'];
  $pos1 = $_GET['pos1'];
  $pos2 = $_GET['pos2'];
  $pos3 = $_GET['pos3'];
  $pos4 = $_GET['pos4'];
  $pos5 = $_GET['pos5'];
  $pos6 = $_GET['pos6'];
  $pos7 = $_GET['pos7'];
  $pos8 = $_GET['pos8'];
  $pos9 = $_GET['pos9'];
  $pos10 = $_GET['pos10'];
  $pos11 = $_GET['pos11'];
  $pos12 = $_GET['pos12'];
  if ($mysqli->connect_errno) 	{
 		die("Failed to connect to MySQL: " . $mysqli->connect_error);
 	}
  if($temperature != 0.0) {
    $sql = "insert into sensor (temperature,humadity,light,sound,pos1,pos2,pos3,pos4,pos5,pos6,pos7,pos8,pos9,pos10,pos11,pos12) values('$temperature','$humadity','$light','$sound','$pos1','$pos2','$pos3','$pos4','$pos5','$pos6','$pos7','$pos8','$pos9','$pos10','$pos11','$pos12')";
    if(!$mysqli->query($sql)) {
      printf("Error : %s\n",$mysqli->error);
    }
    $mysqli->close();
    echo "Success ".time();
  }

?>
