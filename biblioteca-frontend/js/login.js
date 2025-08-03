document.getElementById("login-form").addEventListener("submit", async (e) => {
  e.preventDefault();
  const ra = document.getElementById("ra").value;
  const senha = document.getElementById("senha").value;
  const response = await fetch("http://localhost:8080/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ ra, senha })
  });
  const msg = document.getElementById("mensagem");
  if (response.ok) {
    localStorage.setItem("ra", ra); // Salva o RA
    window.location.href = "painel.html";
  }
  if (response.ok) {
    msg.textContent = "Login realizado com sucesso!";
    msg.style.color = "green";
    setTimeout(() => window.location.href = "painel.html", 1000);
  } else {
    msg.textContent = "RA ou senha inválidos.";
    msg.style.color = "red";
  }
});