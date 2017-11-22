<?php

//Identificar qual o metodo que esta sendo chamado
if ($_SERVER['REQUEST_METHOD'] == 'POST'){

  $conn = mysqli_connect("localhost","root","bcd127", "ContatosAPI");

  $nome=$_POST["nome"];
  $telefone=$_POST["telefone"];
  $imagem=$_POST["foto"];

  $sql="insert into tbl_contato set nome='$nome'
    , telefone='$telefone', imagem ='$imagem';";

  if (mysqli_query($conn, $sql)) {

    echo json_encode(array(
        "sucesso" => true ,
        "mensagem"=> "Inserido com sucesso"));
  } else {

    echo json_encode(array(
        "sucesso" => false ,
        "mensagem" => mysqli_error($conn)));
  }


}else{

  echo json_encode(array(
  "sucesso" => false ,
  "mensagem"=> "Método não suportado"));
}
?>
