var valores = [];
var idAtleta = 0;

function novo() {
    var form = document.getElementById("formulario");
    var lista = document.getElementById("lista");

    form.style.display = "block";
    lista.style.display = "none";

    idAtleta = 0;
    var nome = document.getElementById("nome");
    var modalidade = document.getElementById("modalidade");
    var idade = document.getElementById("idade");
    var clube = document.getElementById("clube");
    nome.value = "";
    modalidade.value = "";
    idade.value = "";
    clube.value = "";

    nome.focus();
}

function alterar(i) {
    var form = document.getElementById("formulario");
    var lista = document.getElementById("lista");

    form.style.display = "block";
    lista.style.display = "none";

    idAtleta = valores[i].idAtleta;
    var nome = document.getElementById("nome");
    var modalidade = document.getElementById("modalidade");
    var idade = document.getElementById("idade");
    var clube = document.getElementById("clube");

    nome.value = valores[i].nome;
    modalidade.value = valores[i].modalidade;
    idade.value = valores[i].idade;
    clube.value = valores[i].clube;

    nome.focus();
}

function salvar() {
    var a = {
        idAtleta: idAtleta,
        nome: document.getElementById("nome").value,
        modalidade: document.getElementById("modalidade").value,
        idade: document.getElementById("idade").value,
        clube: document.getElementById("clube").value
    };

    var metodo;
    if (idAtleta == 0) {
        metodo = "POST";
    } else {
        metodo = "PUT";
    }

    mostraLoading("Aguarde, salvando dados...");
    fetch("http://localhost:8080/JavaRest1/Atleta", {
        method: metodo,
        body: JSON.stringify(a)
    })
    .then(resp => resp.json())
    .then(function(retorno) {
        escondeLoading();
        alert(retorno.mensagem);

        var form = document.getElementById("formulario");
        var lista = document.getElementById("lista");

        form.style.display = "none";
        lista.style.display = "block";

        listar();
    });
}

function excluir(i) {
    idAtleta = valores[i].idAtleta;

    mostraLoading("Aguarde, estamos excluindo...");
    fetch("http://localhost:8080/JavaRest1/Atleta/" + idAtleta, {
        method: "DELETE"
    })
    .then(resp => resp.json())
    .then(function(retorno) {
        escondeLoading();
        alert(retorno.mensagem);

        var form = document.getElementById("formulario");
        var lista = document.getElementById("lista");

        form.style.display = "none";
        lista.style.display = "block";

        listar();
    });
}

function cancelar() {
    var form = document.getElementById("formulario");
    var lista = document.getElementById("lista");

    form.style.display = "none";
    lista.style.display = "block";
}

function listar() {
    var lista = document.getElementById("dados");
    lista.innerHTML = "<tr><td colspan='4'>Aguarde, carregando...</td></tr>";

    fetch("http://localhost:8080/JavaRest1/Atleta")
    .then(resp => resp.json())
    .then(dados => mostrar(dados));
}

function mostraLoading(msg) {
    var loa = document.getElementById("loading");
    var con = document.getElementById("conteudo");

    loa.style.display = "block";
    con.style.display = "none";
    loa.innerHTML = msg;
}

function escondeLoading() {
    var loa = document.getElementById("loading");
    var con = document.getElementById("conteudo");

    loa.style.display = "none";
    con.style.display = "block";
}

function mostrar(dados) {
    valores = dados;
    var lista = document.getElementById("dados");
    lista.innerHTML = "";

    for (var i in dados) {
        lista.innerHTML += "<tr>"
            + "<td>" + dados[i].idAtleta + "</td>"
            + "<td>" + dados[i].nome + "</td>"
            + "<td>" + dados[i].modalidade + "</td>"
            + "<td>" + dados[i].idade + "</td>"
            + "<td>" + dados[i].clube + "</td>"
            + "<td>"
            + "<input type='button' value='Editar' onclick='alterar(" + i + ")'/>"
            + "<input type='button' value='Excluir' onclick='excluir(" + i + ")'/>"
            + "</td>"
            + "</tr>";
    }
}

listar();
