function salvar() {
    let professor = this.lerDados().nomeProfessor;
    let curso = this.lerDados().curso;
    let sala = this.lerDados().sala;
    let date = this.lerDados().data;

    const alocacao = { professor, curso, sala, date };

    if (this.validaCampos(alocacao)) {
        //Método para enviar alocacao para backend via FETCH.
        const response = fetch('http://34.232.52.48:8080/alocacoes', {
            method: "POST", // verificar metodo no backend
            body: JSON.stringify(alocacao),
            headers: { "Content-type": "application/json; charset=UTF-8" }
        })
            .then(response => response.json())
            .then(json => console.log(json))
            .catch(err => console.log(err));
        //FIM DO MÉTODO
        console.log(alocacao);
        setTimeout(() => {
            this.listaTabela();
        }, 2000);
    }
}
function listaTabela() {
    fetch('http://34.232.52.48:8080/alocacoes')
        .then(response => response.json())
        .then(data => {
            const tabela = document.querySelector('tbody');

            var rowCount = tabela.rows.length;

            for (var i = rowCount - 1; i > 0; i--) {
                tabela.deleteRow(i);
            }

            data.forEach(alocacao => {
                const linha = document.createElement('tr');
                const colunaNomeProfessor = document.createElement('td');
                colunaNomeProfessor.textContent = alocacao.professor;
                const colunaNomeCurso = document.createElement('td');
                colunaNomeCurso.textContent = alocacao.curso;

                const colunaData = document.createElement('td');
                const dataNormal = alocacao.date;
                const date = new Date(dataNormal);
                const options = { timeZone: 'UTC', year: 'numeric', month: '2-digit', day: '2-digit' };
                const formattedDate = date.toLocaleDateString('pt-BR', options);
                colunaData.textContent = formattedDate;

                const colunaSala = document.createElement('td');
                colunaSala.textContent = alocacao.sala;
                const colunaId = document.createElement('td');
                colunaId.textContent = alocacao.id;
                const colunaAcoes = document.createElement('td');
                colunaAcoes.classList.add('center');

                let imgDelete = document.createElement('img');
                imgDelete.src = 'img/delete.png';
                imgDelete.onclick = function() {
                    deletar(alocacao.id);
                  };
                colunaAcoes.appendChild(imgDelete);


                linha.appendChild(colunaNomeProfessor);
                linha.appendChild(colunaNomeCurso);
                linha.appendChild(colunaSala);
                linha.appendChild(colunaData);
                linha.appendChild(colunaId);
                linha.appendChild(colunaAcoes);
                tabela.appendChild(linha);
            });
        })
        .catch(error => console.error(error));
  
};
function adicionar(nome) {
    this.arrayProdutos.push(nome);
    this.id++;

    /*this.arrayProdutos.push(curso);
      
 
      this.arrayProdutos.push(sala);
      
 
      this.arrayProdutos.push(data);*/

}

function lerDados() {
    let alocacao = {}
    alocacao.id = null;
    alocacao.nomeProfessor = document.getElementById('nome').value;
    alocacao.curso = document.getElementById('curso').value;
    alocacao.sala = document.getElementById('sala').value;
    alocacao.data = document.getElementById('data').value;
    return alocacao;
}

function validaCampos(alocacao) {
    let mensagem = '';
    if (alocacao.nome == '') {
        mensagem += 'Informe o campo nome \n';
    }

    if (alocacao.curso == '') {
        mensagem += 'Informe o campo curso \n';
    }

    if (mensagem != '') {
        window.alert(mensagem);
        return false;
    }


    return true;
}

function cancelar() {
    document.getElementById('nome').value = '';
    document.getElementById('curso').value = '';
    document.getElementById('sala').value = '';
    document.getElementById('data').value = '';

    document.getElementById('btn-salvar').innerText = 'Salvar';
    this.editId = null;
}

function deletar(id) {
    if (confirm('Deseja deletar o campo ' + id + '?')) {
      // Faz a requisição DELETE para o servidor
      fetch('http://34.232.52.48:8080/alocacoes/' + id, {
        method: 'DELETE'
      })
        .then(response => {
          if (response.ok) {
            // Se a requisição for bem-sucedida, atualiza a tabela
            setTimeout(() => {
                this.listaTabela();
            }, 2000);
          } else {
            // Caso contrário, mostra uma mensagem de erro
            alert('Erro ao excluir a alocação.');
          }
        })
        .catch(err => {
          // Em caso de erro na requisição, mostra uma mensagem de erro
          alert('Erro ao excluir a alocação: ' + err.message);
        });
    }
  }

document.addEventListener('DOMContentLoaded', () => {
    listaTabela();
});