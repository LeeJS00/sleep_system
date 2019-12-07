<?php
  $mysqli = new mysqli("localhost", "root", "1234", "arduino");
  $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");
  $sql="select * from sensor";

  $stmt = $mysqli->query($sql);
  if(!$mysqli->query($sql)) {
    printf("Error : %s\n",$mysqli->error);
  }
    $data = array();
  while($row = $stmt->fetch_assoc()) {
    array_push($data, array('temperature'=>$row["temperature"],
    'humadity'=>$row["humadity"], 'light'=>$row["light"],'sound'=>$row["sound"],
    'pos1'=>$row["pos1"],'pos2'=>$row["pos2"] ,'pos3'=>$row["pos3"],'pos4'=>$row["pos4"],
    'pos5'=>$row["pos5"],'pos6'=>$row["pos6"],'pos7'=>$row["pos7"],'pos8'=>$row["pos8"],
    'pos9'=>$row["pos9"],'pos10'=>$row["pos10"],'pos11'=>$row["pos11"],'pos12'=>$row["pos12"]));
  }

  if (!$android) {
    echo "<pre>";
    print_r($data);
    echo '</pre>';
  }else {
    header('Content-Type: application/json; charset=utf8');
    $json = json_encode(array("webnautes"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
    echo $json;
  }
?>
