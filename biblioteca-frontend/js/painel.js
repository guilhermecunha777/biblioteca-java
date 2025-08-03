document.addEventListener("DOMContentLoaded", () => {
    verEmprestimos();
    document.getElementById("livro-form").addEventListener("submit", adicionarLivro);
    document.getElementById("emprestimo-form").addEventListener("submit", adicionarEmprestimo);
    document.getElementById("aluno-form").addEventListener("submit", adicionarAluno);
});

function ocultarTodosContainers() {
    document.querySelectorAll(".container, .form-container").forEach(el => el.style.display = "none");
}

function verEmprestimos() {
    ocultarTodosContainers();
    document.getElementById("emprestimos-container").style.display = "block";
    carregarEmprestimos();
}

function verLivros() {
    ocultarTodosContainers();
    document.getElementById("livros-container").style.display = "block";
    carregarLivros();
}

function verAlunos() {
    ocultarTodosContainers();
    document.getElementById("alunos-container").style.display = "block";
    carregarAlunos();
}

function mostrarFormularioLivro() {
    ocultarTodosContainers();
    document.getElementById("form-livro-container").style.display = "block";
}

function formAdicionarEmprestimo() {
    ocultarTodosContainers();
    document.getElementById("form-emprestimo-container").style.display = "block";
}

function mostrarFormularioAluno() {
    ocultarTodosContainers();
    document.getElementById("form-alunos-container").style.display = "block";
}

async function carregarEmprestimos() {
    try {
        const res = await fetch("http://localhost:8080/emprestimos/listar");
        const dados = await res.json();
        const container = document.getElementById("emprestimos-container");
        container.innerHTML = "<h2>📖 Empréstimos</h2>";

        dados.forEach(e => {
            const card = document.createElement("div");
            card.className = "card";
            card.innerHTML = `
                <p><strong>Código:</strong> ${e.livro?.id || "N/A"}</p>
                <p><strong>Aluno RA:</strong> ${e.aluno?.ra || "N/A"}</p>
                <p><strong>Data Retirada:</strong> ${e.dataRetirada}</p>
                <p><strong>Data Entrega:</strong> ${e.dataEntrega}</p>
                <p><strong>Devolvido:</strong> ${e.devolvido ? "Sim" : "Não"}</p>
                ${!e.devolvido ? `<button onclick="marcarComoDevolvido(${e.id})">✅ Marcar como Devolvido</button>` : ""}
            `;
            container.appendChild(card);
        });
    } catch (err) {
        alert("Erro ao carregar empréstimos.");
        console.error(err);
    }
}

async function carregarLivros() {
    try {
        const res = await fetch("http://localhost:8080/livros/listar");
        const livros = await res.json();
        const container = document.getElementById("livros-container");
        container.innerHTML = "<h2>📚 Livros</h2>";

        livros.forEach(l => {
            const card = document.createElement("div");
            card.className = "card";
            card.innerHTML = `
                <p><strong>Código:</strong> ${l.id}</p>
                <p><strong>Título:</strong> ${l.titulo}</p>
                <p><strong>Autor:</strong> ${l.autor}</p>
                <p><strong>Categoria:</strong> ${l.categoria}</p>
                <p><strong>Editora:</strong> ${l.editora}</p>
            `;
            container.appendChild(card);
        });
    } catch (err) {
        alert("Erro ao carregar livros.");
        console.error(err);
    }
}

async function carregarAlunos() {
    try {
        const res = await fetch("http://localhost:8080/alunos/listar");
        const alunos = await res.json();
        const container = document.getElementById("alunos-container");
        container.innerHTML = "<h2>👨‍🎓 Alunos</h2>";

        alunos.forEach(a => {
            const card = document.createElement("div");
            card.className = "card";
            card.innerHTML = `
                <p><strong>RA:</strong> ${a.ra}</p>
                <p><strong>Nome:</strong> ${a.nome}</p>
                <p><strong>Email:</strong> ${a.email}</p>
                <p><strong>Telefone:</strong> ${a.telefone}</p>
                <p><strong>Nascimento:</strong> ${a.dataNascimento}</p>
            `;
            container.appendChild(card);
        });
    } catch (err) {
        alert("Erro ao carregar alunos.");
        console.error(err);
    }
}

async function adicionarLivro(event) {
    event.preventDefault();
    const livro = {
        titulo: document.getElementById("titulo").value,
        autor: document.getElementById("autor").value,
        categoria: document.getElementById("categoria").value,
        editora: document.getElementById("editora").value
    };

    try {
        const res = await fetch("http://localhost:8080/livros/cadastrar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(livro)
        });

        if (res.ok) {
            alert("📚 Livro cadastrado!");
            document.getElementById("livro-form").reset();
            verLivros();
        } else {
            alert("Erro ao cadastrar livro.");
        }
    } catch (err) {
        alert("Erro de conexão.");
        console.error(err);
    }
}

async function adicionarEmprestimo(event) {
    event.preventDefault();

    const emprestimo = {
        ra: document.getElementById("ra").value,
        livroId: parseInt(document.getElementById("livroId").value),
        dataRetirada: document.getElementById("dataRetirada").value,
        dataEntrega: document.getElementById("dataEntrega").value
    };

    try {
        const res = await fetch("http://localhost:8080/emprestimos/cadastrar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(emprestimo)
        });

        if (res.ok) {
            alert("📌 Empréstimo registrado!");
            document.getElementById("emprestimo-form").reset();
            verEmprestimos();
        } else {
            alert("Erro ao registrar empréstimo.");
        }
    } catch (err) {
        alert("Erro ao conectar.");
        console.error(err);
    }
}

async function adicionarAluno(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const aluno = Object.fromEntries(formData.entries());

    try {
        const res = await fetch("http://localhost:8080/alunos", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(aluno)
        });

        if (res.ok) {
            alert("🎓 Aluno cadastrado!");
            event.target.reset();
            verAlunos();
        } else {
            alert("Erro ao cadastrar aluno.");
        }
    } catch (err) {
        alert("Erro ao conectar.");
        console.error(err);
    }
}

async function marcarComoDevolvido(id) {
    if (!confirm("Deseja marcar como devolvido?")) return;
    try {
        const res = await fetch(`http://localhost:8080/emprestimos/${id}/devolver`, { method: "PUT" });
        if (res.ok) {
            alert("Livro devolvido.");
            verEmprestimos();
        } else {
            alert("Erro ao devolver.");
        }
    } catch (err) {
        alert("Erro ao conectar.");
        console.error(err);
    }
}
