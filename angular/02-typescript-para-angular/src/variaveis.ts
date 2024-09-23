// tipos primitivos: boolean, number, string
let ligado: boolean = false;
let nome: string = "João";
let idade: number = 30;
let alturar: number = 1.9;

// null undefined
let nulo: null = null;
let indefinido: undefined = undefined;

// any void
let retorno: void;
let retornoView: any = false;

// objeto
let produto: object = {
  name: "João",
  cidate: "SP",
  idade: 30,
};

type ProdutoLoja = {
  nome: string;
  preco: number;
  unidades: number;
};

let meuProduto: ProdutoLoja = {
  nome: "Tênis",
  preco: 89.99,
  unidades: 5,
};

// arrays
let dados: string[] = ["Ana", "Clara"];
let dados2: Array<string> = ["Ana", "Clara"];

let infos: (string | number)[] = ["Ana", 35];

// Tuplas
let boleto: [string, number, number] = ["agua conta", 199.9, 9607950];

// Datas
let aniversario: Date = new Date("2022-12-01 05:00");
console.log(aniversario.toString());
