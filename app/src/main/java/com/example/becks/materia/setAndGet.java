package com.example.becks.materia;

/**
 * Created by becks on 4/19/16.
 */
public class setAndGet {
    public   String name;
    public   int image;
    public  void setName(String foodName){
        this.name=foodName;

    }
    public  String getName(){
        return name;

    }

    public  void setImage(int imagename){
        this.image=imagename;

    }
    public  int getImage() {
        return  image;

    }
}

//    <?php
//    define('HOST','mysql.hostinger.in');
//    define('USER','u359021366_becks');
//    define('PASS','barakatweve');
//    define('DB','u359021366_first');
//$con = mysqli_connect(HOST,USER,PASS,DB);
//        $sql="select * from food";
//        $res = mysqli_query($con,$sql);
//
//        $result = array();
//
//        while($row = mysqli_fetch_array($res)){
//        array_push($result,
//        array(
//        'name'=>$row[0],
//        'address'=>$row[1]
//        ));
//        }
//
//        echo json_encode(array("result"=>$result));
//
//        mysqli_close($con);
//
//        ?>
