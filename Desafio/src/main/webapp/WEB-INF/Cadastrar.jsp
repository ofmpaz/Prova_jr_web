<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Usuário</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css">
    <link rel="stylesheet" href="Estilo.Css">
</head>
<body>
    <div class="container">
        <h1 class="mb-4">Cadastro de Usuário</h1>
        <form id="cadastroForm" method="post" action="UserCadastro">
            <div class="mb-3">
                <label for="nome" class="form-label">Nome:</label>
                <input type="text" id="nome" name="nome" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="sexo" class="form-label">Sexo:</label>
                <select id="sexo" name="sexo" class="form-select" required onchange="limitarSelecao()">
                    <option value="M">Masculino</option>
                    <option value="F">Feminino</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" id="email" name="email" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="celular" class="form-label">Celular:</label>
                <input type="text" id="celular" name="celular" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="senha" class="form-label">Senha:</label>
                <input type="password" id="senha" name="senha" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="confirmarSenha" class="form-label">Confirmar Senha:</label>
                <input type="password" id="confirmarSenha" name="confirmarSenha" class="form-control" required>
            </div>

            <div class="text-center">
                <input type="submit" value="Cadastrar" class="btn btn-primary">
            </div>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#cadastroForm").submit(function(event) {
                event.preventDefault();

                var nome = $("#nome").val();
                var sexo = $("#sexo").val();
                var email = $("#email").val();
                var celular = $("#celular").val();
                var senha = $("#senha").val();
                var confirmarSenha = $("#confirmarSenha").val();

         
                if (senha !== confirmarSenha) {
                    alert("As senhas não são equivalentes");
                    return;
                }

                
                $.ajax({
                    url: "UserCadastro",
                    type: "POST",
                    data: {
                        nome: nome,
                        sexo: sexo,
                        email: email,
                        celular: celular,
                        senha: senha,
                        confirmarSenha: confirmarSenha
                    },
                    success: function(response) {
                        if (response === "successo") {
                        
                            window.location.href = "Login.jsp";
                        } else {
                            alert("Erro ao cadastro");
                        }
                    },
                    error: function(xhr, status, error) {
                        alert("Erro ao cadastro: " + xhr.responseText);
                    }
                });
            });
        });

        function limitarSelecao() {
            var selectElement = document.getElementById("sexo");
            if (selectElement.value.length > 1) {
                selectElement.value = selectElement.value.charAt(0);
            }
        }
    </script>
</body>
</html>
