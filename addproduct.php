<?php
    include('../connection.php');
    // tomar la información enviada por el front-end
    //$id = $_REQUEST['id'];
    $referencia = $_REQUEST['referencia'];
    $nombre = $_REQUEST['nombre'];
    $precio = $_REQUEST['precio'];
    $stock = $_REQUEST['stock'];
    //verificar que el correo no se repita
    $result = mysqli_query($cnn,"SELECT referencia, nombre FROM products WHERE referencia = '$referencia'");
    if (mysqli_num_rows($result) == 0){
        //mysqli_query($cnn,"INSERT INTO customer VALUES ('$idcust','$name','$email','$phone','$passwd')");
        mysqli_query($cnn,"INSERT INTO products (referencia, nombre, precio, stock) VALUES ('$referencia','$nombre','$precio','$stock')");
        $msg = "El producto se ha guardado correctamente...";
    }
    else{
        $msg = "El producto con esta referencia ya existe...";
    }
    echo json_encode($msg);
?>