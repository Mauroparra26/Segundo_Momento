<?php
include("../connection.php");
//Cogemos la id del front 
$id = $_REQUEST['id'];
$sql = "SELECT id From products where id = '$id'";
//en la variable result ejecutamos la conexion y el sql
$result = mysqli_query($cnn, $sql);
if(mysqli_query($result) == 0){
    $query = "DELETE FROM products WHERE id = '$id'";
    mysqli_query($cnn, $query);
    echo "Producto eliminado correctamente.";
}else{
    //si no
    echo "Error al eliminar producto";
}

?>