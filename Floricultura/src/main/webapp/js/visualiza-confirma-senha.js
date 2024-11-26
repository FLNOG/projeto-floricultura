document.getElementById("toggleSenha").addEventListener("click", function () {
    const senhaInput = document.getElementById("confirmarSenha");
    const tipo = senhaInput.type === "password" ? "text" : "password";
    senhaInput.type = tipo;

    this.classList.toggle("bi-eye-fill");
    this.classList.toggle("bi-eye-slash-fill");
});