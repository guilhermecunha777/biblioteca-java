document.getElementById("adm-form").addEventListener("submit", async function (e) {
    e.preventDefault();

    const login = document.getElementById("login").value;
    const senha = document.getElementById("senha").value;

    const data = {
        login: login,
        senha: senha
    };

    try {
        const response = await fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        const result = await response.text();

        if (response.ok) {
            document.getElementById("mensagem").innerText = "Login realizado com sucesso!";
            // Redirecionar para painel, se desejar
            window.location.href = "painel.html";
        } else {
            document.getElementById("mensagem").innerText = `Erro: ${result}`;
        }
    } catch (error) {
        document.getElementById("mensagem").innerText = "Erro de conexão com o servidor.";
    }
});
