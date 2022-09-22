/*
1)Crie as classes AplicacaoError, ContaInexistenteError e SaldoInsuficienteError de
acordo com a hierarquia apresentada em sala.
*/
class AplicacaoError extends Error {
	constructor(message: string) {
		super(message);
	}
}

class ContaInexistenteError extends AplicacaoError {
	constructor(message: string) {
		super(message);
	}
}

class SaldoInsuficienteError extends AplicacaoError {
	constructor(message: string) {
		super(message);
	}
}

/*
4) Crie uma exceção chamada ValorInvalidoError que herda de AplicacaoException e
altere a classe Conta para que ao receber um crédito/depósito, caso o valor seja
menor ou igual a zero, seja lançada a exceção ValorIvalidoException. Altere
também o construtor da classe Conta para que o saldo inicial seja atribuído
utilizando o método creditar.
*/
class ValorInvalidoError extends AplicacaoError {
	constructor(message: string) {
		super(message);
	}
}

/*
6) Crie uma exceção chamada PoupancaInvalidaError que herda de Error. Altere
então o método render juros da classe Banco para que caso a conta não seja uma
poupança, a exceção criada seja lançada.
*/
class PoupancaInvalidaError extends Error {
	constructor(message: string) {
		super(message);
	}
}

class Conta {
	private _numero: String;
	private _saldo: number = 0;

	constructor(numero: String, saldoInicial: number) {
		this._numero = numero;
		this.depositar(saldoInicial);
	}

	get numero(): String {
		return this._numero;
	}

	get saldo(): number {
		return this._saldo;
	}

	sacar(valor: number): void {
		if (this._saldo < valor) {
			throw new SaldoInsuficienteError("Saldo insuficiente");
		}

		this.validarValor(valor);
		this._saldo = this._saldo - valor;
	}

	depositar(valor: number): void {
		this.validarValor(valor);
		this._saldo = this._saldo + valor;
	}

	transferir(contaDestino: Conta, valor: number): void {
		this.sacar(valor);
		contaDestino.depositar(valor);
	}

	/*
	5)Refatore o código criando um método privado chamado validarValor onde um valor 
	é passado como parâmetro e caso o mesmo seja menor ou igual a zero, seja lançada 
	uma exceção. Altere também os métodos sacar e creditar para chamar esse método de 
	validação em vez de cadaum lançar a sua própria exceção, evitando assim a 
	duplicação de código.
	*/
	private validarValor(valor: number): number {
		if (valor > 0) {
			return valor;
		}
		throw new ValorInvalidoError("Valor inválido")
	}
}

class Poupanca extends Conta {
	private _taxaJuros: number;

	constructor(numero: String, saldo: number, taxaJuros: number) {
		super(numero, saldo);
		this._taxaJuros = taxaJuros;
	}

	public renderJuros(): void {
		this.depositar(this.saldo * this._taxaJuros / 100);
	}

	get taxaJuros(): number {
		return this._taxaJuros
	}
}

class Banco {
	private _contas: Conta[] = [];

	inserir(conta: Conta): void {
		try {
			this.consultar(conta.numero);
		} catch (error: any) {
			this._contas.push(conta);
		}
	}
	/*
	2) Implemente na classe Banco os métodos consultar e consultarPorIndice para que,
	caso a conta procurada não seja encontrada, a exceção ContaInexistente seja
	lançada.
	*/
	consultar(numero: String): Conta {
		for (let conta of this._contas) {
			if (conta.numero == numero) {
				return conta;
			}
		}
		throw new ContaInexistenteError("Conta inexistente");
	}

	private consultarPorIndice(numero: String): number {
		for (let i: number = 0; i < this._contas.length; i++) {
			if (this._contas[i].numero == numero) {
				return i;
			}
		}
		throw new ContaInexistenteError("Conta inexistente");
	}

	/* 
	3) Altere os métodos alterar, creditar, sacar, transferir, renderJuros removendo os
	“ifs/elses”, pois caso haja exceção no método consultar, os respectivos códigos
	não serão mais necessários.
	*/
	alterar(conta: Conta): void {
		let indice: number = this.consultarPorIndice(conta.numero);
		this._contas[indice] = conta;
	}

	excluir(numero: string): void {
		let indice: number = this.consultarPorIndice(numero);

		for (let i: number = indice; i < this._contas.length; i++) {
			this._contas[i] = this._contas[i + 1];
			this._contas.pop();
		}
	}

	depositar(numero: String, valor: number): void {
		let contaConsultada = this.consultar(numero);
		contaConsultada.depositar(valor);
	}

	sacar(numero: String, valor: number): void {
		let contaConsultada = this.consultar(numero);
		contaConsultada.sacar(valor);
	}

	transferir(numeroDebito: string, numeroCredito: string, valor: number) {
		let contaCredito: Conta = this.consultar(numeroCredito);
		let contaDebito: Conta = this.consultar(numeroDebito);

		contaDebito.transferir(contaCredito, valor);
	}

	calcularQuantidadeContas(): number {
		return this._contas.length;
	}

	calcularTotalSaldos(): number {
		let totalSaldo: number = 0;
		for (let conta of this._contas) {
			totalSaldo += conta.saldo;
		}

		return totalSaldo;
	}

	calcularMediaSaldos() {
		return this.calcularTotalSaldos() / this.calcularQuantidadeContas();
	}

	renderJuros(numero: String) {
		let contaConsultada = this.consultar(numero);

		if (contaConsultada instanceof Poupanca) {
			let poupanca: Poupanca = <Poupanca>contaConsultada;
			poupanca.renderJuros();
			return;
		}

		throw new PoupancaInvalidaError("Esta conta não é poupança");
	}
}

/*
7) Crie uma aplicação com opções de menu para todos os métodos da classe Banco passíveis de uso.
*/
import * as readline from 'node:readline';
import { stdin as input, stdout as output } from 'node:process';
import { Interface } from 'node:readline';

const banco: Banco = new Banco();

let rl: Interface = readline.createInterface({ input, output });

console.log(
	"Opções: (1) inserir | (2) consultar | (3) alterar | (4) excluir | (5) depositar | (6) sacar | (7) transferir \n"
	+ "(8) quant. contas | (9) total saldos | (10) media saldo | (11) render juros | (12) sair \n"
);

const main = () => {
	rl.question("Selecione uma opção: ", (opcao) => {
		switch (opcao) {
			case '1':
				inserir();
				main();
				break;
			case '2':
				consultar();
				main();
				break;
			case '3':
				alterar();
				main();
				break;
			case '4':
				excluir();
				main();
				break;
			case '5':
				depositar();
				main();
				break;
			case '6':
				sacar();
				main();
				break;
			case '7':
				transferir();
				main();
				break;
			case '8':
				quantidadeContas();
				main();
				break
			case '9':
				totalSaldos();
				main();
				break
			case '10':
				mediaSaldos();
				main();
				break
			case '11':
				renderJuros();
				main();
				break
			case '12':
				rl.close();
				break;
			default:
				console.log("Opção inválida!");
				main();
		}
	})
}

main();

function inserir() {
	let numeroConta: string = "";
	let saldoInicial: number = 5;

	rl.question("Número da conta: ", (numero) => {
		numeroConta = numero
		rl.question("Saldo da conta: ", (saldo) => {
			saldoInicial = Number(saldo);
			rl.question("Cadastrar como poupança? [s/n]: ", (resposta) => {
				try {
					switch (resposta.toLocaleLowerCase()) {
						case 's':
							banco.inserir(new Poupanca(numeroConta, saldoInicial, 1));
							break;
						case 'n':
							banco.inserir(new Conta(numeroConta, saldoInicial));
							break;
						default:
							throw Error("Opção inválida")
					}
				} catch (error: any) {
					console.log(error.message);
				} finally {
					console.log("Operação finalizada\n");
					main();
				}
			})
		})
	})
}

function consultar() {
	rl.question("Número da conta: ", (numero) => {
		try {
			console.log(banco.consultar(numero));
		} catch (error: any) {
			console.log(error.message);
		} finally {
			console.log("Operação finalizada\n");
			main();
		}
	})
}

function alterar() {
	let numeroConta: string = "";
	let saldoInicial: number = 5;

	rl.question("Número da conta: ", (numero) => {
		numeroConta = numero
		rl.question("Saldo da conta: ", (saldo) => {
			saldoInicial = Number(saldo);
			rl.question("Alterar como poupança? [s/n]: ", (resposta) => {
				try {
					switch (resposta.toLocaleLowerCase()) {
						case 's':
							banco.alterar(new Poupanca(numeroConta, saldoInicial, 1));
							break;
						case 'n':
							banco.alterar(new Conta(numeroConta, saldoInicial));
							break;
						default:
							throw Error("Opção inválida")
					}
				} catch (error: any) {
					console.log(error.message);
				} finally {
					console.log("Operação finalizada\n");
					main();
				}
			})
		})
	})
}

function excluir() {
	rl.question("Número da conta: ", (numero) => {
		try {
			banco.excluir(numero);
		} catch (error: any) {
			console.log(error.message);
		} finally {
			console.log("Operação finalizada\n");
			main();
		}
	})
}

function depositar() {
	let numeroConta: string = "";
	let valorDeposito: number = 5;

	rl.question("Número da conta: ", (numero) => {
		numeroConta = numero
		rl.question("Valor do depósito: ", (valor) => {
			valorDeposito = Number(valor);
			try {
				banco.depositar(numeroConta, valorDeposito);
			} catch (error: any) {
				console.log(error.message);
			} finally {
				console.log("Operação finalizada\n");
				main();
			}
		})
	})
}

function sacar() {
	let numeroConta: string = "";
	let valorSaque: number = 0;

	rl.question("Número da conta: ", (numero) => {
		numeroConta = numero
		rl.question("Valor do saque: ", (valor) => {
			valorSaque = Number(valor);
			try {
				banco.sacar(numeroConta, valorSaque);
			} catch (error: any) {
				console.log(error.message);
			} finally {
				console.log("Operação finalizada\n");
				main();
			}
		})
	})
}

function transferir() {
	let numeroContaDebito: string = "";
	let numeroContaCredito: string = "";
	let valorTransferencia: number = 0;

	rl.question("Número da conta debito: ", (debito) => {
		numeroContaDebito = debito
		rl.question("Valor da conta crédito: ", (credito) => {
			numeroContaCredito = credito;
			rl.question("Valor da transferência: ", (valor) => {
				valorTransferencia = Number(valor);
				try {
					banco.transferir(numeroContaDebito, numeroContaCredito, valorTransferencia);
				} catch (error: any) {
					console.log(error.message);
				} finally {
					console.log("Operação finalizada\n");
					main();
				}
			})
		})
	})
}

function quantidadeContas() {
	try {
		console.log(banco.calcularQuantidadeContas());
	} catch (error: any) {
		console.log(error.message);
	} finally {
		console.log("Operação finalizada\n");
		main();
	}
}

function totalSaldos() {
	try {
		console.log(banco.calcularTotalSaldos());
	} catch (error: any) {
		console.log(error.message);
	} finally {
		console.log("Operação finalizada\n");
		main();
	}
}

function mediaSaldos() {
	try {
		console.log(banco.calcularMediaSaldos());
	} catch (error: any) {
		console.log(error.message);
	} finally {
		console.log("Operação finalizada\n");
		main();
	}
}

function renderJuros() {
	rl.question("Número da conta: ", (numero) => {
		try {
			banco.renderJuros(numero);
		} catch (error: any) {
			console.log(error.message);
		} finally {
			console.log("Operação finalizada\n");
			main();
		}
	})
}
