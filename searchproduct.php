<?php
    include("../connection.php");
    $referencia = $_REQUEST['referencia'];    //tomar valores de parte del front 
    $nombre = $_REQUEST['nombre'];

    $sql = "SELECT id, referencia,nombre,precio,stock From products where referencia = '$referencia' and nombre = '$nombre'";

    //dentro de la variable res, se ejecuta la conexion y el sql juntos
    $res = mysqli_query($cnn, $sql);

    //si lo encuentra 
    if(mysqli_num_rows($res) > 0){
        $row = $res->fetch_all(MYSQLI_ASSOC);//dentro de la variable row FUNCION PARA DEVOLVER SOLO UN VALOR 
        echo json_encode($row);
    }else{
    //si no lo encuentra
        echo "El producto con esta referencia/nombre no existe";
    }
?>