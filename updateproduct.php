<?php
 include('../connection.php');
    $id = $_REQUEST['id'];
    $referencia = $_REQUEST['referencia'];
    $nombre = $_REQUEST['nombre'];
    $precio = $_REQUEST['precio'];
    $stock = $_REQUEST['stock'];


    $productos = mysqli_query ($cnn, "SELECT * FROM products WHERE id = '$id'");

    if(mysqli_num_rows($productos)>0){
        mysqli_query($cnn, "UPDATE products SET referencia='$referencia', nombre='$nombre', precio='$precio', stock='$stock' WHERE id = '$id'");
        echo "Se ha actualizado el producto.";
   }
    else{
        echo "Error actualizando el producto.";
    }
?>